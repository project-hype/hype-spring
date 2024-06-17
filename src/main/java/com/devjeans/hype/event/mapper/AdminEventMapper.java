package com.devjeans.hype.event.mapper;

import com.devjeans.hype.event.domain.EventVO;

public interface AdminEventMapper {


	// Event CRUD
	public EventVO selectEventById(Long eventId);
	public int insertEvent(EventVO event);
	public int updateEvent(EventVO event);
	public int deleteEvent (Long eventId);	
	
}
