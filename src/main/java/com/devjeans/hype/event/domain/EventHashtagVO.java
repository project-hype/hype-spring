package com.devjeans.hype.event.domain;

import java.util.List;

import lombok.Data;

@Data
public class EventHashtagVO {
	
	private Long eventId;
	private Long hashtagId;
	
	private List<HashtagVO> hashtagList;
	private List<EventVO> eventList;

}
