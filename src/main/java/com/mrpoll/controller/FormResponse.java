package com.mrpoll.controller;

import java.util.Date;
import java.util.List;


public class FormResponse {
    private Integer idPoll;
    private Date responseDate;
    private List<FormChoiceResponse> formChoiceResponses;

    public List<FormChoiceResponse> getFormChoiceResponses() {
        return formChoiceResponses;
    }

    public void setFormChoiceResponses(List<FormChoiceResponse> formChoiceResponses) {
        this.formChoiceResponses = formChoiceResponses;
    }

    

    public Integer getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(Integer idPoll) {
        this.idPoll = idPoll;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
    
    
    
    
}
