package com.devjeans.hype.event.domain;

import lombok.Data;

@Data
public class EventHashtagVO {
	
	private Long eventId;
	private Long hashtagId;
	
	private HashtagVO hashtag;
	private EventVO event;

}
