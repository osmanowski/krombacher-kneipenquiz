package com.groupfive.krombacherkneipenquiz;


import com.groupfive.krombacherkneipenquiz.models.FragenPaket;
import com.groupfive.krombacherkneipenquiz.models.Kneipe;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaAuditing
public class KrombacherKneipenquizApplication {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(KrombacherKneipenquizApplication.class, args);
        ÜbergangsklassefuerMethoden ads = new ÜbergangsklassefuerMethoden();




    }


}
