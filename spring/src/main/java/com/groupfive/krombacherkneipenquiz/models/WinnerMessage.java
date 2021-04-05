package com.groupfive.krombacherkneipenquiz.models;

public class WinnerMessage {
    public WinnerMessage(){

    }
    public WinnerMessage(String name, double score){
        this.name = name;
        this.punkte = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    private String name;
    private double punkte;
}
