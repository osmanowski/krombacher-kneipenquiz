package com.groupfive.krombacherkneipenquiz.models;

public class ScoreMessage {
    public ScoreMessage(){

    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private String user_id;
    private int score;

}
