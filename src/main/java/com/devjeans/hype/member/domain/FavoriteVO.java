package com.devjeans.hype.member.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FavoriteVO {
	private Long memberId;
	private Long eventId;
	private Date createDate;
}
