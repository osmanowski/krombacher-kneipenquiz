package com.groupfive.krombacherkneipenquiz.controller;

import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.Frage;
import com.groupfive.krombacherkneipenquiz.models.FragenPaket;
import com.groupfive.krombacherkneipenquiz.models.HelloMessage;
import com.groupfive.krombacherkneipenquiz.models.ScoreMessage;
import com.groupfive.krombacherkneipenquiz.repositories.FrageRepository;
import com.groupfive.krombacherkneipenquiz.repositories.FragenPaketRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class FrageController {

    @Autowired
    FrageRepository frageRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FragenPaketRepository fragenPaketRepository;

    // Get All Fragen
    @GetMapping("/fragen")
    public List<Frage> getAllFragen() {
        return frageRepository.findAll();
    }

    @GetMapping("/hello")
    public HelloMessage getHelloMessage() {
        HelloMessage hm = new HelloMessage();
        hm.setName("rundenID");
        hm.setUser_id("benutzername");
        return hm;
    }
    @GetMapping("/scoremessage")
    public ScoreMessage getScoreMessage() {
        ScoreMessage sm = new ScoreMessage();
        sm.setScore(30);
        sm.setUser_id("User1");
        return sm;
    }

    // get eine frage
    @GetMapping("/frage/{id}")
    public Frage getFrageById(@PathVariable(value = "id") long frageId) {
        Frage frage = frageRepository.findById(frageId)
                .orElseThrow(() -> new ResourceNotFoundException("Frage", "id", frageId));
        return frage;
    }


    @PostMapping("/addFrage")
    public Frage addFrage(@Valid @RequestBody Frage frage) {

        for (FragenPaket fp : frage.getFragenPakete())
        {
                FragenPaket fp2 = fragenPaketRepository.getOne(fp.getId());
                fp2.addFrage(frage);
                fragenPaketRepository.save(fp2);
        }
        return frage;
    }

    // Delete a Note
    @DeleteMapping("/deletefrage/{id}")
    public void deleteFrage(@PathVariable(value = "id") Long fragenId) {
        Frage frage = frageRepository.findById(fragenId)
                .orElseThrow(() -> new ResourceNotFoundException("Frage", "id", fragenId));
        frageRepository.delete(frage);

    }


}