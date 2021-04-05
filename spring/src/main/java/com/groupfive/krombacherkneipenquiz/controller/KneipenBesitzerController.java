package com.groupfive.krombacherkneipenquiz.controller;

import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.login.SecurityConfiguration;
import com.groupfive.krombacherkneipenquiz.models.Kneipenbesitzer;
import com.groupfive.krombacherkneipenquiz.repositories.KneipenbesitzerRepository;
import com.groupfive.krombacherkneipenquiz.ÜbergangsklassefuerMethoden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class KneipenBesitzerController {
    @Autowired
    private KneipenbesitzerRepository kneipenbesitzerRepository;
    @Autowired
    SecurityConfiguration securityConfiguration;

    @PostMapping("/signupbesitzer")
    public Kneipenbesitzer signUpKneipenBesitzer(@Valid @RequestBody String[] daten) throws Exception {
        Kneipenbesitzer kneipenbesitzer = new Kneipenbesitzer(daten[0],daten[2],securityConfiguration.passwordhashen(daten[1]));
        //kneipenbesitzer.setIp(ÜbergangsklassefuerMethoden.getIp());
        kneipenbesitzer.setKneipenId(1);
        kneipenbesitzer.setRole("ROLE_Kneipenbesitzer");
        return kneipenbesitzerRepository.save(kneipenbesitzer);
    }


    @PutMapping("/addkneipetobesitzer/{id}")
    public Kneipenbesitzer addKneipetobesitzer(@PathVariable(value = "id") Long userId, @Valid @RequestBody Kneipenbesitzer neuekneipe) {
        Kneipenbesitzer kneipenbesitzer = kneipenbesitzerRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        kneipenbesitzer.setKneipenId(neuekneipe.getKneipenId());
        Kneipenbesitzer kneipenbesitzer1 = kneipenbesitzerRepository.save(kneipenbesitzer);
        return kneipenbesitzer1;
    }

    @GetMapping("/allbesitzer")
    public List<Kneipenbesitzer> getAllKneipenBesitzer() {
        return kneipenbesitzerRepository.findAll();
    }

    @GetMapping("/besitzer/{benutzername}")
    public Kneipenbesitzer getKneipenbesitzer(@PathVariable(value="benutzername") String benutzername) {
        return kneipenbesitzerRepository.findKneipenbesitzerByBenutzername(benutzername);
    }

}
