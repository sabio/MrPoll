package com.mrpoll.controller;

import java.util.ArrayList;
import java.util.List;


public class QuestionResult {
    private Integer id;
    private String questionText;
    private List<ChoiceResult> choiceResults;
    
    
    public void addChoiceResult(ChoiceResult cr){
        if(this.choiceResults == null) this.choiceResults = new ArrayList<>();
        this.choiceResults.add(cr);
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
    
    public List<ChoiceResult> getChoiceResults() {
        return choiceResults;
    }

    public void setChoiceResults(List<ChoiceResult> choiceResults) {
        this.choiceResults = choiceResults;
    }
}
