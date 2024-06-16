package com.devjeans.hype.member.domain;

import java.util.Date;

import lombok.Data;

@Data
public class FavoriteVO {
	private Long member_id;
	private Long event_id;
	private Date create_date;
}
