package com.devjeans.hype.event.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devjeans.hype.event.service.EventService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@AllArgsConstructor
@RequestMapping("/event/*")
public class EventController {
	
	private EventService service;
	
	@GetMapping("/main")
	public void getListTopView(Model model) {
		log.info("list");
		model.addAttribute("list", service.getListTopView());
	}

}
