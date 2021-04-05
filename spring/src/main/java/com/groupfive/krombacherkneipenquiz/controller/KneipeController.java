package com.groupfive.krombacherkneipenquiz.controller;

import com.groupfive.krombacherkneipenquiz.exception.ResourceNotFoundException;
import com.groupfive.krombacherkneipenquiz.models.Kneipe;
import com.groupfive.krombacherkneipenquiz.repositories.KneipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class KneipeController {
    @Autowired
    private KneipeRepository kneipeRepository;

    @PostMapping("/addkneipe")
    public Kneipe addKneipe(@Valid @RequestBody Kneipe kneipe) {
        return kneipeRepository.save(kneipe);
    }

    @GetMapping("/getkneipe/{kneipenid}")
    public Kneipe getKneipe(@PathVariable (value = "kneipenid") long kneipenid)
    {
        return kneipeRepository.findById(kneipenid).orElseThrow(() -> new ResourceNotFoundException("Kneipe", "id",kneipenid));
    }

    @GetMapping("/getallkneipen")
    public List<Kneipe> getAllKneipen()
    {
        return kneipeRepository.findAll();
    }

    // Delete
    @DeleteMapping("/deletekneipe/{id}")
    @CrossOrigin
    public void deleteKneipe(@PathVariable(value = "id") Long Kneipenid) {
        Kneipe kneipe = kneipeRepository.findById(Kneipenid)
                .orElseThrow(() -> new ResourceNotFoundException("Kneipe", "id", Kneipenid));
        kneipeRepository.delete(kneipe);
    }
}
