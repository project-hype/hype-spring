package com.devjeans.hype.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CreateEventRequest {

	@NotNull
	private Long eventId;
	@NotNull
	private Long branchId;
	@NotNull
	private Long eventTypeId;
	@NotNull
	private Long categoryId;
	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private String imageUrl;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	@NotNull
	private int viewCount;
	@NotNull
	private String detailAddress;
}
