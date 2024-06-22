package com.devjeans.hype.event.dto;


import lombok.Data;

@Data
public class StarScoreRequest {
    public Long eventId;
    public Long memberId;
    public String action;
    public Double score;
    
}
