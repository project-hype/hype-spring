package com.devjeans.hype.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class StarScoreVO {
	
	private Long memberId;
	private Long eventId;
	private double score;
	private Date createDate;

}
