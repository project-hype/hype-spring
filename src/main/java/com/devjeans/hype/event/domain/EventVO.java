package com.devjeans.hype.event.domain;

import java.util.Date;

import lombok.Data;

@Data
public class EventVO {

	private Long eventId;
	private Long branchId;
	private Long eventTypeId;
	private Long categoryId;
	private String title;
	private String content;
	private String imageUrl;
	private Date startDate;
	private Date endDate;
	private int viewCount;
	private String detailAddress;
	
}