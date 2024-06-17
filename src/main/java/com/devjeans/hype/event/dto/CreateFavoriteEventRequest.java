package com.devjeans.hype.event.dto;

import lombok.Data;

@Data
public class CreateFavoriteEventRequest {
	
	private Long eventId;
	private Long memberId;
	

}
