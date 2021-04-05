package com.groupfive.krombacherkneipenquiz.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Spieler extends User implements Serializable
{
    private double score;

    public Spieler(String benutzername, String eMail, String pw) {
        super(benutzername, eMail, pw);
        this.setRole("ROLE_Spieler");
        this.setScore(0);
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Spieler() {
    }



}
