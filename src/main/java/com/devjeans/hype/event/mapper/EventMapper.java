package com.devjeans.hype.event.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.devjeans.hype.event.domain.EventVO;

public interface EventMapper {
	
	@Select("SELECT dummy FROM DUAL")
	public List<Object> getDual();
	public Object getDual2();

	@Select("SELECT event_id FROM EVENT")
	public List<EventVO> getList();
	
	public List<EventVO> getTopViewEvents();
	
	public List<EventVO> getEventsByDate(Date date);
	
}
