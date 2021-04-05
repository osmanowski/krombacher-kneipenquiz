package com.groupfive.krombacherkneipenquiz.models;

import java.util.Random;

public class FragenMessage {

    public FragenMessage(){}

    public FragenMessage(int FragenNr, String frage, String antwortA, String antwortB, String antwortC, String antwortD, int timeleft, boolean neu){
        this.frage = frage;
        this.antwortA = antwortA;
        this.antwortB = antwortB;
        this.antwortC = antwortC;
        this.antwortD = antwortD;
        this.timeleft = timeleft;
        this.fragenNr = FragenNr;
        this.neu = neu;
    }



    private int timeleft;

    public int getFragenNr() {
        return fragenNr;
    }

    public void setFragenNr(int fragenNr) {
        this.fragenNr = fragenNr;
    }

    private int fragenNr;
    private String frage;
    private String antwortA;
    private String antwortB;
    private String antwortC;
    private String antwortD;

    public boolean isNeu() {
        return neu;
    }

    public void setNeu(boolean neu) {
        this.neu = neu;
    }

    private boolean neu;

    public int getTimeleft() {
        return timeleft;
    }


    public void setTimeleft(int timeleft) {
        this.timeleft = timeleft;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getAntwortA() {
        return antwortA;
    }

    public void setAntwortA(String antwortA) {
        this.antwortA = antwortA;
    }

    public String getAntwortB() {
        return antwortB;
    }

    public void setAntwortB(String antwortB) {
        this.antwortB = antwortB;
    }

    public String getAntwortC() {
        return antwortC;
    }

    public void setAntwortC(String antwortC) {
        this.antwortC = antwortC;
    }

    public String getAntwortD() {
        return antwortD;
    }

    public void setAntwortD(String antowrtD) {
        this.antwortD = antowrtD;
    }

}
