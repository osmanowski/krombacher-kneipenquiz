package com.groupfive.krombacherkneipenquiz.controller;

import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.Frage;
import com.groupfive.krombacherkneipenquiz.models.FragenPaket;
import com.groupfive.krombacherkneipenquiz.repositories.FrageRepository;
import com.groupfive.krombacherkneipenquiz.repositories.FragenPaketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FragenPaketController
{
    @Autowired
    FragenPaketRepository fragenPaketRepository;

    @Autowired
    FrageRepository frageRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;


    @PostMapping("/addPaket")
    public FragenPaket addPaket(@Valid @RequestBody FragenPaket paket) {
        return fragenPaketRepository.save(paket);
    }

    //Fragepaket für eine Quizrunde holen               Funzt hoffentlich
    @GetMapping("/getpaketfuerquiz/{paketid},{anzahl}")
    public List<Frage> getPaketFuerQuiz(@PathVariable(value = "paketid") int[] paketid,@PathVariable(value = "anzahl") int anzahlVonFragen)
    {
        Random rand = new Random();
        String sql = "select frage_id from fragenpaket_frage where paket_id =" + paketid[0];     // sql begriff für jdbc
        List<Frage> fragenliste = new ArrayList<>();        // liste mit fragen die später zurückgegeben wird
        for(int x = 1 ; x < paketid.length;x++)             // falls mehr als 1 paket angegebn wurde wir der sql begriff erweitert das er mehrere pakete erfasst
        {
            sql = sql + "or paket_id ="+ paketid[x];
        }

        List<Long> idliste = jdbcTemplate.queryForList(sql,Long.class);
        List<Long> alreadyUsedIDs = new ArrayList<Long>();
        for (int i = 0 ; i < anzahlVonFragen ; i++)                         // für die geforderte Anzzahl werden nun random fragen aus der liste gepickt
        {                                                                   //
            long frageid = idliste.get(rand.nextInt(idliste.size()));
            while (true){
                if( alreadyUsedIDs.contains(frageid)){
                    frageid = idliste.get(rand.nextInt(idliste.size()));
                }
                else if (idliste.size() <= alreadyUsedIDs.size()){
                    break;
                }
                else {
                    break;}
            }
            Frage frage = frageRepository.findById(frageid).orElseThrow(() -> new ResourceNotFoundException("Frage", "id",""));
            fragenliste.add(frage);
            alreadyUsedIDs.add(frageid);

        }
        return fragenliste;
    }

    @GetMapping("/getfragenpaket/{paketid}")
    public List<Frage> getFragenPaket(@PathVariable(value = "paketid") int paketid)
    {
        List<Long> idliste = jdbcTemplate.queryForList("select frage_id from fragenpaket_frage where paket_id ="+paketid,Long.class);
        List<Frage> fragenliste = new ArrayList<>();
        for (int i = 0; i < idliste.size();i++)
        {
            fragenliste.add(frageRepository.findById(idliste.get(i)).orElseThrow(() -> new ResourceNotFoundException("Frage", "id","")));
        }
        return  fragenliste;
    }

    @GetMapping("/getRandomFragen/{paketid},{anzahl}")
    public  List<Frage> getRandomFragen(@PathVariable(value = "paketid") long paketid, @PathVariable(value = "anzahl") int anz)
    {
        List<Frage> result;
        FragenPaket fp = fragenPaketRepository.findById(paketid).orElseThrow(() -> new ResourceNotFoundException("Frage", "id",""));
        result = fp.getRandomFragen(anz);
        return result;
    }


    // Get All Fragen
    @GetMapping("/allFragenPakete")
    public List<FragenPaket> getAllFragenPakete() {
        return fragenPaketRepository.findAll();
    }

    @PostMapping("/addfragetopaket/{paketid},{frageid}")
    public void addFrageToPaket(@PathVariable(value = "paketid") Long paketid,@PathVariable(value = "frageid") Long fragenid)
    {
        String sql = "INSERT INTO fragenpaket_frage (paket_id, frage_id) VALUE ('"+ paketid +"','"+fragenid+"')";
        jdbcTemplate.update(sql);

    }

}
