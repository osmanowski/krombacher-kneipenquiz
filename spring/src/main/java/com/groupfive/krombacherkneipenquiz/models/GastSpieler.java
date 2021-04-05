package com.groupfive.krombacherkneipenquiz.models;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class GastSpieler extends User
{
    private int Score;
    private String benutzername;
    private String ip;


    public GastSpieler()
    {

    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    @Override
    public String getBenutzername() {
        return benutzername;
    }

    @Override
    public void setBenutzername(String benutzername) {
        this.benutzername = benutzername;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
