package com.groupfive.krombacherkneipenquiz.models;

public class RundenTimer {

    private int timeLeft;

    public RundenTimer(int timeLeft) { this.timeLeft = timeLeft;}

    public RundenTimer(){}

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }
}
