package com.groupfive.krombacherkneipenquiz.models;

public class AntwortMessage {

    public AntwortMessage()
    {

    }
    public AntwortMessage(String antwort, String erklaerung)
    {
        this.antwort = antwort;
        this.erklaerung = erklaerung;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    private String antwort;

    private String erklaerung;
}
