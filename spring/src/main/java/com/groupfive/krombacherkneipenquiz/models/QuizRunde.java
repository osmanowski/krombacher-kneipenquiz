package com.groupfive.krombacherkneipenquiz.models;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class QuizRunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date time;
    private boolean enabled = false;
    private long kneipenid;
    private long paketid;
    private long winner;        //Spielerid
    private String ip;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    private boolean started = false;

    public int getFragenAnzahl() {
        return fragenAnzahl;
    }

    public void setFragenAnzahl(int fragenAnzahl) {
        this.fragenAnzahl = fragenAnzahl;
    }

    private int fragenAnzahl;

    public void setSpielerliste(List<Spieler> spielerliste) {
        this.spielerliste = spielerliste;
    }

    @ManyToMany
    private List<Spieler> spielerliste;

    @ManyToMany (cascade = {CascadeType.ALL})
    private List<Frage> fragenliste = new ArrayList<>();



    public QuizRunde( Date time, long kneipe, long paket) {

        this.time = time;
        this.kneipenid = kneipe;
        this.paketid = paket;



    }

    public QuizRunde() {
    }

    public void addSpielerToQuiz(Spieler spieler)
    {
        spielerliste.add(spieler);
    }

    public void removeSpielerFromQuiz(long spielerid)
    {
        for(int i = 0 ;i < spielerliste.size(); i++)
        {
            if (spielerliste.get(i).getBenutzerId() == spielerid)
            {
                spielerliste.remove(i);
                return;
            }
        }
    }

    public List<Spieler> getSpielerliste()
    {
        return spielerliste;
    }


    public long getId() {
        return id;
    }

    /*
    public void setId(int id) {
        this.id = id;
    }
       */
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getKneipenid() {
        return kneipenid;
    }

    public void setKneipenid(long kneipenid) {
        this.kneipenid = kneipenid;
    }

    public long getPaketid() {
        return paketid;
    }

    public void setPaketid(long paketid) {
        this.paketid = paketid;
    }

    public long getWinner() {
        long check = 0;
        /*
        Spieler spieler = spielerliste.get(0);
        for (int j = 0; j < spielerliste.size(); j++) {
            if (spieler.getScore() < spielerliste.get(j).getScore()) {
                spieler = spielerliste.get(j);
            }
            }
            check = (long) spieler.getScore();

        if (spielerliste.size() > 0 && check >0) {
            spieler = spielerliste.get(0);
            for (int i = 0; i < spielerliste.size(); i++) {
                if (spieler.getScore() < spielerliste.get(i).getScore()) {
                    spieler = spielerliste.get(i);
                }
            }

            return spieler.getBenutzerId();
        }
        */

        return 0;
    }

    public void setWinner(long winner) {
        this.winner = winner;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Frage> getFragenliste() {
        return fragenliste;
    }

    public void setFragenliste(List<Frage> fragenliste) {
        this.fragenliste = fragenliste;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
