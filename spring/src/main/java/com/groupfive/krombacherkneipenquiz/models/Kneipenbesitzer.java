package com.groupfive.krombacherkneipenquiz.models;

import com.groupfive.krombacherkneipenquiz.Umgebung.KneipenBesitzerUmgebung;
import com.groupfive.krombacherkneipenquiz.ÜbergangsklassefuerMethoden;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Kneipenbesitzer extends User
{
    private long kneipenId;

    //nehmen wir erstmal an dass jeder Besitzer nur eine Kneipe besitzt
    public Kneipenbesitzer(String benutzername, String eMail, String pw) {
        super(benutzername, eMail, pw);

    }



    public Kneipenbesitzer(){
        try {
            this.setIp(ÜbergangsklassefuerMethoden.getIp());
        }catch (Exception e) {
            System.out.println("error");
        }
    }

    public long getKneipenId() {
        return kneipenId;
    }

    public void setKneipenId(long kneipenId) {
        this.kneipenId = kneipenId;
    }

    public QuizRunde startQuizrunde() {
        QuizRunde quizRunde = new QuizRunde();
        quizRunde.setKneipenid(getKneipenId());
        return quizRunde;
    }
}
