package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.mapper.EventMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
	
	private EventMapper mapper;

	@Override
	public List<EventVO> getListTopView() {
		log.info("조회수 top10 조회");
		return mapper.getTopViewEvents();
	}

	@Override
	public List<EventVO> getListByDate(Date date) {
		log.info("날짜별 조회 ");
		return mapper.getEventsByDate(date);
	}

}
