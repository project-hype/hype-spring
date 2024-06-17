package com.devjeans.hype.event.domain;

import java.util.Date;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

@Data
public class StarScoreVO {
	
	private Long memberId;
	private Long eventId;
	private double score;
	private Date createDate;

	private EventVO event;
	private MemberVO member;
}
