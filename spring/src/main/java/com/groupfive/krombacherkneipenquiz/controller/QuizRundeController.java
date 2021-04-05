package com.groupfive.krombacherkneipenquiz.controller;
import com.groupfive.krombacherkneipenquiz.hostclient.Ip;

import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.*;
import com.groupfive.krombacherkneipenquiz.repositories.FragenPaketRepository;
import com.groupfive.krombacherkneipenquiz.repositories.QuizRundeRepository;
import com.groupfive.krombacherkneipenquiz.repositories.SpielerRepository;
import com.groupfive.krombacherkneipenquiz.ÜbergangsklassefuerMethoden;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class QuizRundeController
{
    @Autowired
    QuizRundeRepository quizRundeRepository;

    @Autowired
    FragenPaketRepository fragenPaketRepository;

    @Autowired
    SpielerRepository spielerRepository;

    @PostMapping("/addQuizrunde")
    public QuizRunde addQuizRunde(@Valid @RequestBody QuizRunde runde) throws Exception {
        FragenPaket fp = fragenPaketRepository.findById(runde.getPaketid()).orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", runde.getPaketid()));
        List<Frage> fragen = fp.getRandomFragen(runde.getFragenAnzahl());
        runde.setFragenliste(fragen);
        runde.setEnabled(true);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(runde.getTime());
        calendar.add(Calendar.HOUR, -1);
        runde.setTime(calendar.getTime());

        
        //test
        return quizRundeRepository.save(runde);
    }


    @GetMapping("/getQuizRunde/{id}")
    public QuizRunde getQuizRunde(@PathVariable(value = "id") long rundenId)
    {
        return quizRundeRepository.findById(rundenId)
                .orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", rundenId));
    }

    @GetMapping("/getWinner/{id}")
    public Spieler getWinner(@PathVariable(value = "id") long rundenId)
    {
        Spieler result = null;
        QuizRunde runde = quizRundeRepository.findById(rundenId)
                .orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", rundenId));

        double cs = 0;
        for (Spieler s: runde.getSpielerliste()) {
            if (s.getScore() > cs)
            {
                cs = s.getScore();
                result = s;
            }
        }

        if (cs == 0)
        {
            result = new Spieler();
            result.setScore(0);
            result.setBenutzername("kein Sieger");
        }

        return result;
    }





    // highscore von Runde    returnt die id vom gewinner
    @PostMapping("/gethighscoreofround/{id}")
    public long getHighscoreOfRound(@PathVariable(value = "id") long rundenId)
    {
        QuizRunde runde = quizRundeRepository.findById(rundenId)
                .orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", rundenId));
        return runde.getWinner();
    }

    @PostMapping("/getspielerliste/{quizid}")
    public List<Spieler> getSpielerliste(@PathVariable(value = "quizid") long quizId)
    {
        QuizRunde runde = quizRundeRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", quizId));
        return runde.getSpielerliste();
    }

    @PostMapping("/addspielertoquiz/{quizid},{spielerid}")
    public void addSpielerToQuiz(@PathVariable(value = "quizid") long quizId,@PathVariable(value = "spielerid") long spielerId)
    {
        System.out.println(quizId + " " + spielerId);
        Spieler spieler = spielerRepository.findByBenutzerId(spielerId);
        spieler.setScore(0);
        spielerRepository.save(spieler);
        QuizRunde quiz = quizRundeRepository.findById(quizId).orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", quizId));
        quiz.addSpielerToQuiz(spieler);
        quizRundeRepository.save(quiz);
    }


    // user score erhöhen
    @PostMapping("/addtouserscore/{userid},{quizid},{score}")
    public void addToUserScore(@PathVariable(value = "userid") long userid, @PathVariable(value = "quizid") long rundenid,
                               @PathVariable(value = "score") double scoreamount)
    {
        QuizRunde runde = quizRundeRepository.findById(rundenid).orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", rundenid));
        List<Spieler> spielerliste = runde.getSpielerliste();
        for (Spieler spieler : spielerliste) {
            if (spieler.getBenutzerId() == userid) {
                spieler.setScore(spieler.getScore() + scoreamount);
                break;
            }
        }

    }

    @PostMapping("/addScore/{userid}, {score}")
    public void addScore(@PathVariable(value = "userid") long userid, @PathVariable(value = "score") int score)
    {
        Spieler curr_spieler = spielerRepository.findByBenutzerId(userid);
        curr_spieler.setScore(curr_spieler.getScore() + score);
        spielerRepository.save(curr_spieler);
    }
/*
    @PostMapping("/startQuizRunde/{zeitpunkt},{pakete},{anzahl}")
    public void startQuizrunde(@PathVariable(value = "zeitpunkt") Date startPunkt,@PathVariable(value = "pakete") int[] pakete,@PathVariable(value = "anzahl") int anzahl)
    {
        QuizRunde quizRunde = new QuizRunde();
        quizRunde.setTime(startPunkt);
        FragenPaketController fpc = new FragenPaketController();
        List<Frage> fragenliste = fpc.getPaketFuerQuiz(pakete,anzahl);
        Timer timerQuiz = new Timer();
        TimerTask quizrunde = new TimerTask() {
            int uebrig = anzahl;
            int zaehler = 0;
            @Override
            public void run()
            {
                uebrig--;
                zaehler ++;
                if (uebrig == 0)
                {
                    timerQuiz.cancel();
                }
                System.out.println("Frage:"+ zaehler);
                frageAnzeigen(fragenliste.get(zaehler));


            }
        };

        System.out.println("Willkommen beim Krombacher-KneipenQuiz");
        timerQuiz.scheduleAtFixedRate(quizrunde, 2000, 30000);      // 2 sek wird wilkommen angezeigt
                                                                                    // alle 30 sek neue frage

    }

*/
    // Frage ausgeben mit Timer
    // 5 Sek wird die frage angezeigt .... Dann alle Antworten und ein Countdown von 15 sek startet<- muss dann mit Actionlistener unterbrochen werden bei Auswahl
    public static void frageAnzeigen(Frage frage)      // ggf. Methode umbenennen
    {
        Timer timerfrage = new Timer();
        TimerTask countdown = new TimerTask()       // Hierführ java.util.* Importieren

        {
            int sek = 15;
            @Override
            public void run()
            {
                System.out.println("Du hast noch " + sek + " Sekunden Übrig");
                sek--;
                // ActionListener der bei auswahl den Countdown abbricht
                // übrige sek -> methodenaufruf   -> addToUserScore(sek);
                // if(frage.istRichtig("")) //getroffe auswahl übergeben
                //{
                //addToUserScore(sek);
                //}
                if(sek == 0 )
                {
                    timerfrage.cancel();
                    System.out.println("Du hast keine Antwort ausgewählt");
                    System.out.println(frage.getErklaerung());
                }
            }
        };

        TimerTask antworten = new TimerTask()
        {
            @Override
            public void run() {
                Random rnd = new Random();
                String antA = frage.getAntwortA();
                String antB = frage.getAntwortB();
                String antC = frage.getAntwortC();
                String antD = frage.getAntwortD();
                String aa = "";
                String ab = "";
                String ac = "";
                String ad = "";
                List<String> antwortliste = new ArrayList<>();
                antwortliste.add(antA); antwortliste.add(antB); antwortliste.add(antC); antwortliste.add(antD);
                for( int y = 0; y < 4;y++)
                {
                    switch(y)
                    {
                        case 0:
                            int a = rnd.nextInt(antwortliste.size());
                             aa = "A:"+ antwortliste.get(a);
                            antwortliste.remove(a);
                        break;
                        case 1:
                            int b = rnd.nextInt(antwortliste.size());
                             ab = "B:"+ antwortliste.get(b);
                            antwortliste.remove(b);
                            break;
                        case 2:
                            int c = rnd.nextInt(antwortliste.size());
                            ac = "C:"+ antwortliste.get(c);
                            antwortliste.remove(c);
                            break;
                        case 3:
                            int d = rnd.nextInt(antwortliste.size());
                            ad = "D:"+ antwortliste.get(d);
                            antwortliste.remove(d);
                            break;


                    }

                }

                System.out.println(aa);
                System.out.println(ab);
                System.out.println(ac);
                System.out.println(ad);
                timerfrage.scheduleAtFixedRate(countdown,0,1000);
            }

        };

        System.out.println(frage.getFrage());
        timerfrage.schedule(antworten,5000);
    }





    @GetMapping("/ip/{ip}")//ipp
    public QuizRunde getQuizRundebyIp(@PathVariable(value = "ip") String ip) {
        return quizRundeRepository.findByIp(ip);
    }

    @GetMapping("/quizrundebyIp/{ip}")
    public  List<QuizRunde> getQuizrundeEnabledByIp(@PathVariable(value = "ip") String ip){
        return quizRundeRepository.findByIpAndEnabled(ip, true);
    }

    @GetMapping("/beispiel")
    public static void rundePresentieren()
    {
        Timer timerQuiz = new Timer();
        TimerTask quizrunde = new TimerTask() {
            int uebrig = 3;
            int zaehler = 0;
            @Override
            public void run()
            {
                uebrig--;
                zaehler ++;
                if (uebrig == 0)
                {
                    timerQuiz.cancel();
                }
                System.out.println("Frage:"+ zaehler);
                fragePresentieren();
            }
        };

        System.out.println("Willkommen beim Krombacher-Kneipen Quiz");
        timerQuiz.scheduleAtFixedRate(quizrunde, 2000, 20000);
    }

    public static void fragePresentieren()
    {
        Timer timer = new Timer();
        TimerTask countdown = new TimerTask()
        {
            int sek = 10;
            @Override
            public void run()
            {
                System.out.println("Du hast noch " + sek + " Sekunden Übrig");
                sek--;
                if(sek == 0 )
                {
                    timer.cancel();
                    System.out.println("Du hast keine Antwort ausgewählt");
                    System.out.println("Antwort A wäre richtig gewesen\n Du bekommst leider keine Punkte");
                    System.out.println("Erklärung:\"Link spielt die zentrale Rolle in der Videospiel-Serie \"The Legend of Zelda\".\n Schon seit 1986 versucht er in verschiedenen Spielen Prinzessin Zelda vor Dämon Ganon zu retten.");

                }
            }
        };

        TimerTask antworten = new TimerTask()
        {
            @Override
            public void run() {
                System.out.println("A: The Legend of Zelda");
                System.out.println("B: Halo");
                System.out.println("C: Far Cry");
                System.out.println("D: Uncharted");
                timer.scheduleAtFixedRate(countdown,0,1000);

            }

        };
        System.out.println("Frage: In welcher Spiele-Serie ist \"Link\" die Hauptﬁgur?");
        timer.schedule(antworten,5000);
    }



}
