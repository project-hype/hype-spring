package com.devjeans.hype.event.domain;

import java.util.List;

import lombok.Data;

@Data
public class BannerVO {
	
	private Long eventId;
	private int orderPriority;
	
	private List<EventVO> event;

}
