package com.mrpoll.controller;

import java.util.Date;
import java.util.List;


public class FormResponse {
    private Integer idPoll;
    private String ipAddress;
    private Date responseDate;
    private List<FormChoiceResponse> responses;
    

    public List<FormChoiceResponse> getResponses() {
        return responses;
    }

    public void setResponses(List<FormChoiceResponse> responses) {
        this.responses = responses;
    }
    

    public Integer getIdPoll() {
        return idPoll;
    }

    public void setIdPoll(Integer idPoll) {
        this.idPoll = idPoll;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
    
    
    
    
}
