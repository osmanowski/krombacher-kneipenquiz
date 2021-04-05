package com.groupfive.krombacherkneipenquiz.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BasicAuthController {

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Hier muss irgendwie die Rolle übergeben werden
        return new AuthenticationBean("You are authenticated",auth.getAuthorities().toString());
    }
}