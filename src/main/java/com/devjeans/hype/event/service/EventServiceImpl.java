package com.devjeans.hype.event.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devjeans.hype.event.dto.GetListTopViewEventDTO;
import com.devjeans.hype.event.mapper.EventMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
	
	private EventMapper mapper;

	@Override
	public List<GetListTopViewEventDTO> getListTopView() {
		log.info("get Event List");
		return mapper.getTopViewEvents();
	}

}
