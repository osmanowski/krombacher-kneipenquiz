package com.groupfive.krombacherkneipenquiz.controller;


import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.Frage;
import com.groupfive.krombacherkneipenquiz.models.GastSpieler;
import com.groupfive.krombacherkneipenquiz.models.Kneipe;
import com.groupfive.krombacherkneipenquiz.repositories.GastSpielerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class GastSpielerController {

    @Autowired
    GastSpielerRepository gastSpielerRepository;


    // Get All gastspieler
    @GetMapping("/gastspieler")
    public List<GastSpieler> getAllGastSpieler() {
        return gastSpielerRepository.findAll();
    }

    // get ein Gastspieler
    @GetMapping("/gastspieler/{id}")
    public GastSpieler getGastById(@PathVariable(value = "id") long gastId) {
        return gastSpielerRepository.findById(gastId)
                .orElseThrow(() -> new ResourceNotFoundException("Gastspieler", "id", gastId));
    }



    @PostMapping("/addgastspieler")
    public GastSpieler addGastSpieler(@Valid @RequestBody GastSpieler gastSpieler) {
        return gastSpielerRepository.save(gastSpieler);
    }



}
