package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;

public interface EventService {
	
	public List<EventVO> getListTopView();
	
	public List<EventVO> getListByDate(Date date);
	
}
