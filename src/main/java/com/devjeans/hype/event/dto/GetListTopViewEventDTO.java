package com.devjeans.hype.event.dto;

import java.util.Date;

import lombok.Data;

@Data
public class GetListTopViewEventDTO {
	
	private Long eventId;
	private Long branchId;
	private Long eventTypeId;
	private String imageUrl;
	private String title;
	private Date startDate;
	private Date endDate;
	private String branchName;
	private String eventTypeName;	
	private int viewCount;

}
