package com.groupfive.krombacherkneipenquiz.hostclient;

import java.util.*;

public class QuestionList {

    private List<HostQuestion> questions;

    public QuestionList(){
        questions = new ArrayList<HostQuestion>();
    }


    public void addPackageToList(int packageID){

    }

    public void addQuestion(HostQuestion q){
        this.questions.add(q);
    }

    public int getSize(){
        return questions.size();
    }
}
