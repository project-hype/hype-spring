package com.devjeans.hype.event.domain;

import java.util.List;

import lombok.Data;

@Data
public class HashtagVO {
	
	private Long hashtagId;
	private String hashtagName;
	
	private List<EventHashtagVO> eventHashtagList;
}
