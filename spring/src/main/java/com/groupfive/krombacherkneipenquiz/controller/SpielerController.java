package com.groupfive.krombacherkneipenquiz.controller;


import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.login.SecurityConfiguration;
import com.groupfive.krombacherkneipenquiz.models.Spieler;
import com.groupfive.krombacherkneipenquiz.models.User;
import com.groupfive.krombacherkneipenquiz.repositories.SpielerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class SpielerController {

    @Autowired
    private SpielerRepository spielerRepository;
    @Autowired
    SecurityConfiguration securityConfiguration;


    @PostMapping("/signupplayer")
    public Spieler signUpSpieler(@Valid @RequestBody Spieler spieler) {

        return spielerRepository.save(spieler);
    }

    @PostMapping("/regus")
    public Spieler regUser(@Valid @RequestBody String[] daten) {
        String pas = securityConfiguration.passwordhashen(daten[1]);
        System.out.println(pas);
        Spieler spieler = new Spieler(daten[0],daten[2],pas);
        spieler.setScore(0);
        spieler.setRole("ROLE_Spieler");
        spieler.setEnable(true);
        return spielerRepository.save(spieler);
    }


    @GetMapping("/getuser/{benutzername}")
    public Spieler loginSpieler(@PathVariable(value="benutzername") String benutzername) throws ResourceNotFoundException{
        return spielerRepository.findSpielerByBenutzername(benutzername);
    }


    @GetMapping("/allspieler")
        public List<Spieler> getAllSpieler() {
            return spielerRepository.findAll();
        }


    @GetMapping("/getuserById/{id}")
    public Spieler loginSpieler(@PathVariable(value="id") long id) throws ResourceNotFoundException{
        return spielerRepository.findByBenutzerId(id);
    }


}
