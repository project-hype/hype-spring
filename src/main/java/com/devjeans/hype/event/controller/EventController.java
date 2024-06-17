package com.devjeans.hype.event.controller;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.service.EventService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@AllArgsConstructor
@RequestMapping(value="/event/*", produces="MediaType.APPLICATION_JSON_UTF8_VALUE")
public class EventController {
	
	private EventService service;
	
//	@GetMapping("/list/")
//	public ResponseEntity<List<EventVO>> getListTopView(Model model) {
//		model.addAttribute("list", service.getListTopView());
//	}
//	
//	public void getListByDate(Date date) {
//		
//	}

}
