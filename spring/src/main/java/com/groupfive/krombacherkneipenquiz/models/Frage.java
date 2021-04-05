package com.groupfive.krombacherkneipenquiz.models;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "frage")
@EntityListeners(AuditingEntityListener.class)
public class Frage implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;




    private String frage, antwortA,antwortB,antwortC,antwortD,richtig,erklaerung;




    @ManyToMany (mappedBy = "fragen")
    private Set<FragenPaket> fragenPakete = new HashSet<>();




    public Frage(String frage,String AntwortA,String AntwortB,String AntwortC,String AntwortD,String korrekteAntwort,String erklaerung)
    {


        this.frage = frage;
        this.antwortA = AntwortA;
        this.antwortB = AntwortB;
        this.antwortC = AntwortC;
        this.antwortD = AntwortD;
        this.richtig = korrekteAntwort;
        this.erklaerung = erklaerung;
    }

    public Frage() {

    }

    public void mixUp()
    {
        String[] antworten = {antwortA, antwortB, antwortC, antwortD};
        int rightPos = 0;
        String[] antwortenNeu = {antwortA, antwortB, antwortC, antwortD};


        switch (richtig)
        {
            case "A":
                rightPos = 0;
                break;
            case "B":
                rightPos = 1;
                break;
            case "C":
                rightPos = 2;
                break;
            case "D":
                rightPos = 3;
                break;
            default:
                break;
        }


        List<Integer> picked = new ArrayList<>();
        int item;
        for (int x = 1; x <= 4; x++)
        {

            do {
                item = new Random().nextInt(4);

            }
            while (picked.contains(item));
            System.out.println(item);
            picked.add(item);
        }

        int y = 0;
        boolean posChanged = false;
        for (int i: picked) {
            antwortenNeu[i] = antworten[y];
            if (y == rightPos && posChanged == false)
            {
                rightPos = i;
                posChanged = true;
            }
            y++;
        }

        antwortA = antwortenNeu[0];
        antwortB = antwortenNeu[1];
        antwortC = antwortenNeu[2];
        antwortD = antwortenNeu[3];
        switch (rightPos)
        {
            case 0:
                richtig = "A";
                break;
            case 1:
                richtig = "B";
                break;
            case 2:
                richtig = "C";
                break;
            case 3:
                richtig = "D";
                break;
            default:
                break;

        }

        System.out.println(richtig);


    }

    public boolean istRichtig(String Antwort)       // Buchstabe wird übergeben
    {
        if(this.richtig.equals(Antwort))            // Methode zur abfrage ob eine ausgewählte Antwort die Rictige ist
        {
            return true;
        }
        return false;

    }

    public long getId() {
        return this.id;
    }                                                     // Getter und setter...

    public String getFrage() {
        return frage;
    }

    public String getAntwortA() {
        return antwortA;
    }

    public String getAntwortB() {
        return antwortB;
    }

    public String getAntwortC() {
        return antwortC;
    }

    public String getAntwortD() {
        return antwortD;
    }

    public String getRichtig() {
        return richtig;
    }

    public String getErklaerung() {
        return erklaerung;
    }


    public Set<FragenPaket> getFragenPakete() { return fragenPakete; }

    public void setId(int fragen_ID) {
        this.id = fragen_ID;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public void setAntwortA(String antwortA) {
        this.antwortA = antwortA;
    }

    public void setAntwortB(String antwortB) {
        this.antwortB = antwortB;
    }

    public void setAntwortC(String antwortC) {
        this.antwortC = antwortC;
    }

    public void setAntwortD(String antwortD) {
        this.antwortD = antwortD;
    }

    public void setRichtig(String richtig) {
        this.richtig = richtig;
    }

    public void setErklaerung(String erklaerung) {
        this.erklaerung = erklaerung;
    }


    public void setFragenPakete(Set<FragenPaket> fragenPakete) { this.fragenPakete = fragenPakete; }


}
