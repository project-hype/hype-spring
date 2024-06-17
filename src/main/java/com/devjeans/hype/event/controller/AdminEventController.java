package com.devjeans.hype.event.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.service.AdminEventService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/admin/event")
@Log4j
@AllArgsConstructor
public class AdminEventController {

	private AdminEventService service;
	
	@GetMapping("/list")
	public String list(Criteria cri) throws Exception {
		List<EventVO> eventList = service.getEventListWithPaging(cri);
		
		return "success";
	}
	
}
