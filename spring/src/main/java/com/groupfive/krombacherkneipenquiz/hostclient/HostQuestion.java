package com.groupfive.krombacherkneipenquiz.hostclient;

public class HostQuestion {

    private String  Question;
    private String  answerOne;
    private String  answerTwo;
    private String  answerThree;
    private String  answerFour;
    private int     correctAnswer;

    public HostQuestion(String q, String one, String two, String three, String four, int c){
        Question        = q;
        answerOne       = one;
        answerTwo       = two;
        answerThree     = three;
        answerFour      = four;
        correctAnswer   = c;

    }

    @Override
    public String toString(){
        return Question + " " + answerOne + " " + answerTwo + " " + answerThree + " " + " "
                + answerFour + " Korrekt: " + correctAnswer;
    }

}
