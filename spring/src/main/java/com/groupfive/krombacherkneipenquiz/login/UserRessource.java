package com.groupfive.krombacherkneipenquiz.login;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRessource {

    @GetMapping("/testsp")
    public String user()
    {
        return "Hallo Spieler";
    }

    @GetMapping("/testad")
    public String admin()
    {
        return "Hallo Admin";
    }

    @GetMapping("/testkn")
    public String besitzer()
    {
        return "Hallo Kneipenbesitzer";
    }


    @GetMapping("/testga")
    public String gast()
    {
        return "Hallo Gast-User";
    }

}
