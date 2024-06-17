package com.devjeans.hype.event.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.CreateEventRequest;
import com.devjeans.hype.event.dto.GetEventDetailResponse;
import com.devjeans.hype.event.dto.GetEventListResponse;
import com.devjeans.hype.event.service.AdminEventService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 어드민 행사관리 컨트롤러
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * </pre>
 */

@RestController
@RequestMapping(
		value="/admin/event",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class AdminEventController {

	private AdminEventService service;
	
	@GetMapping("/list")
	public GetEventListResponse getEventList(Criteria cri) throws Exception {
		List<EventVO> eventList = service.getEventListWithPaging(cri);
		
		return new GetEventListResponse(eventList);
	}
	
	@GetMapping("/detail/{eventId}")
	public GetEventDetailResponse getEventDetail(@PathVariable("eventId") Long eventId) throws Exception{
		EventVO event = service.getEventById(eventId);
		
		return new GetEventDetailResponse(event);
	}
	
	@PostMapping("")
	public void createEvent(@RequestBody @Valid CreateEventRequest request,
			BindingResult bs) throws Exception {
		log.info(bs);
		if (bs.hasErrors()) {
			log.info("error!");
		}
		log.info(request);
	}
	
}
