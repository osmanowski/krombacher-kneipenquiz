package com.groupfive.krombacherkneipenquiz.Handler;

import com.groupfive.krombacherkneipenquiz.controller.QuizRundeController;
import com.groupfive.krombacherkneipenquiz.controller.SpielerController;
import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.*;
import com.groupfive.krombacherkneipenquiz.repositories.FrageRepository;
import com.groupfive.krombacherkneipenquiz.repositories.QuizRundeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.BinaryMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;

@Controller
@EnableScheduling
public class WebSocketHandler extends AbstractWebSocketHandler {

    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private QuizRundeController quizRundeController;

    @Autowired
    private QuizRundeRepository quizRundeRepository;

    @Autowired
    private SpielerController spielerController;

    @Autowired
    private FrageRepository frageRepository;


    @MessageMapping("/score")
    public void addScore(ScoreMessage message) throws Exception{
        Spieler curr_Player = spielerController.loginSpieler(message.getUser_id());
        quizRundeController.addScore(curr_Player.getBenutzerId(), message.getScore());
    }

    @MessageMapping("/test")
    public void test (SimpMessageHeaderAccessor sha, HelloMessage message) throws Exception {
        template.convertAndSend("/topic/greetings/"+message.getName(), "klappt");
        System.out.println(sha.getUser().getName());
    }

    @MessageMapping("/registrieren")
    public void registrieren (HelloMessage message) throws Exception {

        System.out.println("registriert");
        Spieler currPlayer = spielerController.loginSpieler(message.getUser_id());
        List<Spieler> spielerList = quizRundeController.getSpielerliste(Long.parseLong(message.getName()));

        if (spielerList.contains(currPlayer)==false)
        {
            quizRundeController.addSpielerToQuiz(Long.parseLong(message.getName()), currPlayer.getBenutzerId());
        }

        System.out.println("registriert");

    }


    @MessageMapping("/hello")
    public void greeting (HelloMessage message) throws Exception {

        //System.out.println("connected");
        Spieler currPlayer = spielerController.loginSpieler(message.getUser_id());
        List<Spieler> spielerList = quizRundeController.getSpielerliste(Long.parseLong(message.getName()));
        /*
        boolean spielerVorhanden = false;
        for (Spieler s: spielerList) {
            if (s.getBenutzerId() == )
            {
                spielerVorhanden = true;
            }


         */








        QuizRunde runde = quizRundeRepository.findById(Long.parseLong(message.getName())).orElseThrow(() -> new ResourceNotFoundException("Quizrunde", "id", message.getName()));



        if (runde.isStarted() == false){
            runde.setStarted(true);
            quizRundeRepository.save(runde);
            for (Frage f: runde.getFragenliste()) {
                f.mixUp();
            }


            Date d = new Date();

            Timer timer = new Timer();

            int curr_time = Math.toIntExact((d.getTime() - runde.getTime().getTime())/1000);

            int zeitProFrage = 15;
            int zeitZwischenFragen = 10;
            int maxTime = (zeitProFrage + zeitZwischenFragen)*runde.getFragenliste().size();
            System.out.println(maxTime);


            TimerTask waiter = new TimerTask()
            {
                int runtimer = curr_time;
                int fragenNr = 0;
                int timeleft;
                @Override
                public void run()
                {

                    if (maxTime>runtimer)
                    {
                        if(runtimer <= 0 )
                        {

                            template.convertAndSend("/topic/greetings/" + runde.getId(), new RundenTimer(runtimer*-1));


                        }
                        else
                        {

                            fragenNr = Math.floorDiv(runtimer, zeitProFrage+zeitZwischenFragen);



                            System.out.println(fragenNr);
                            timeleft = zeitProFrage+zeitZwischenFragen - runtimer%(zeitProFrage+zeitZwischenFragen);
                            System.out.println("TimeLeft" + timeleft);
                            if (timeleft == zeitProFrage + zeitZwischenFragen)
                            {
                                Frage curr_Frage = runde.getFragenliste().get(fragenNr);
                                template.convertAndSend("/topic/greetings/" + runde.getId(), new FragenMessage(fragenNr+1, curr_Frage.getFrage(), curr_Frage.getAntwortA(),curr_Frage.getAntwortB(), curr_Frage.getAntwortC(), curr_Frage.getAntwortD(),timeleft - zeitZwischenFragen, true));

                            }
                            else if(timeleft >= zeitZwischenFragen)
                            {
                                //Fragen senden
                                Frage curr_Frage = runde.getFragenliste().get(fragenNr);
                                template.convertAndSend("/topic/greetings/" + runde.getId(), new FragenMessage(fragenNr+1, curr_Frage.getFrage(), curr_Frage.getAntwortA(),curr_Frage.getAntwortB(), curr_Frage.getAntwortC(), curr_Frage.getAntwortD(),timeleft - zeitZwischenFragen, false));
                            }
                            else if(timeleft == zeitZwischenFragen-1)
                            {
                                //Antwort senden
                                Frage curr_Frage = runde.getFragenliste().get(fragenNr);
                                template.convertAndSend("/topic/greetings/" + runde.getId(), new AntwortMessage(curr_Frage.getRichtig(), curr_Frage.getErklaerung()));
                            }
                            else
                            {
                                //Wartezeit
                            }
                        }

                    }
                    else
                    {
                        Spieler winner = quizRundeController.getWinner(runde.getId());
                        template.convertAndSend("/topic/greetings/" +runde.getId(), new WinnerMessage(winner.getBenutzername(), winner.getScore()));
                        this.cancel();
                    }

                    //System.out.println(runtimer);
                    runtimer++;
                }
            };




            timer.scheduleAtFixedRate(waiter,0,1000);

        }

    }


    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("New Text Message Received");
        //session.sendMessage(message);
        //fragePresentieren(session);

        long zeitFuerFrage = 20000;
        long zeitZwischenFragen = 5000;


        List<Frage> fragenliste = new ArrayList<>();
        Date roundStart = null;





        if (message.getPayload() != "")
        {


            try {
                String url = "jdbc:mysql://theflexblade.com:3306/krombacher_quiz";
                Connection conn = DriverManager.getConnection(url,"kromb","bromb1234");
                Statement stmt = conn.createStatement();
                ResultSet rs;

                rs = stmt.executeQuery("SELECT * FROM quiz_runde JOIN quiz_runde_fragenliste on quiz_runde.id = quiz_runde_fragenliste.quiz_runde_id  Join frage on fragenliste_id = frage.id WHERE quiz_runde_id = " + message.getPayload());
                while ( rs.next() ) {
                    roundStart = rs.getTimestamp("time");

                    Frage frage = new Frage();

                    frage.setFrage(rs.getString("frage"));
                    frage.setAntwortA(rs.getString("antwortA"));
                    frage.setAntwortB(rs.getString("antwortB"));
                    frage.setAntwortC(rs.getString("antwortC"));
                    frage.setAntwortD(rs.getString("antwortD"));
                    frage.setErklaerung(rs.getString("erklaerung"));
                    frage.setRichtig(rs.getString("richtig"));
                    fragenliste.add(frage);
                }



                conn.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }

            //session.sendMessage(new TextMessage("" + roundStart.getTime()));
            System.out.println(roundStart.getTime());
            //roundStart.wait(roundStart.getTime());

            //Wait until Round Starts
            Date d = new Date();
            System.out.println(d.getTime());

            System.out.println(roundStart.getTime());
            long timeToWait = roundStart.getTime() - d.getTime();
            System.out.println(timeToWait);
            int sekToWait = Math.toIntExact(timeToWait/1000);

            Timer timer = new Timer();




            TimerTask waiter = new TimerTask()
            {
                int sek = sekToWait;
                @Override
                public void run()
                {
                    //System.out.println("Du hast noch " + sek + " Sekunden Übrig");

                    if(sek <= 0 )
                    {
                        this.cancel();
                        //System.out.println("Du hast keine Antwort ausgewählt");
                        try {
                            session.sendMessage(new TextMessage("Start!"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // nach der Wartezeit wird die Runde gestartet

                    }
                    try {
                        if(session.isOpen())
                            session.sendMessage(new TextMessage("Noch " + sek + " Sekunden bis zum Start"));
                        else
                            timer.cancel();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sek--;

                }
            };




            timer.scheduleAtFixedRate(waiter,0,1000);



            d = new Date();
            //int sekInRound = Math.toIntExact((d.getTime() - roundStart.getTime())/1000);
            long timeInRound = (d.getTime() - roundStart.getTime());

            TimerTask fragen = new TimerTask() {
                List<Frage> fragen = fragenliste;
                int fragenanzahl = fragenliste.size();
                int iterator = 0;

                @Override
                public void run() {
                    if (iterator < fragenanzahl)
                    {
                        try {
                            session.sendMessage(new TextMessage(fragen.get(iterator).getFrage()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        try {
                            session.sendMessage(new TextMessage("Ende"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            session.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        this.cancel();
                    }

                    iterator++;
                }
            };

            timer.scheduleAtFixedRate(fragen, timeToWait, 10000);


        }




    }

    @Override
    protected void handleBinaryMessage(WebSocketSession session, BinaryMessage message) throws Exception {
        System.out.println("New Binary Message Received");
        session.sendMessage(message);
    }




    private void fragePresentieren(WebSocketSession session)
    {
        Timer timer = new Timer();
        TimerTask countdown = new TimerTask()
        {
            int sek = 10;
            @Override
            public void run()
            {
                System.out.println("Du hast noch " + sek + " Sekunden Übrig");


                try {
                    session.sendMessage(new TextMessage("Du hast noch " + sek + " Sekunden Übrig"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

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
                try {
                    session.sendMessage(new TextMessage("A: The Legend of Zelda"));
                    session.sendMessage(new TextMessage("B: Halo"));
                    session.sendMessage(new TextMessage("C: Far Cry"));
                    session.sendMessage(new TextMessage("D: Uncharted"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("A: The Legend of Zelda");
                System.out.println("B: Halo");
                System.out.println("C: Far Cry");
                System.out.println("D: Uncharted");
                timer.scheduleAtFixedRate(countdown,0,1000);

            }

        };
        System.out.println("Frage: In welcher Spiele-Serie ist \"Link\" die Hauptﬁgur?");
        try {
            session.sendMessage(new TextMessage("Frage: In welcher Spiele-Serie ist \"Link\" die Hauptﬁgur?"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer.schedule(antworten,5000);
    }



}
