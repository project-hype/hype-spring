package com.devjeans.hype.event.controller;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.CreateFavoriteEventRequest;
import com.devjeans.hype.event.dto.GetBannerMainEventListResponse;
import com.devjeans.hype.event.dto.GetMainEventListResponse;
import com.devjeans.hype.event.service.EventService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 메인페이지 행사 컨트롤러
 * @author 정은지 
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17   정은지         최초 생성
 * </pre>
 */

@RestController
@RequestMapping(value="/event", produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class EventController {
	
	private EventService service;
	
	@GetMapping("/list/top")
	public GetMainEventListResponse getListTopView() throws Exception {
		List<EventVO> list = service.getListTopView();
		log.info(list);

		return new GetMainEventListResponse(list);
	}
	
	@GetMapping("/list/banner")
	public GetBannerMainEventListResponse getListBanner() throws Exception {
		List<BannerVO> list = service.getListBanner();
		
		return new GetBannerMainEventListResponse(list);
	}
	
	@GetMapping("/list/{date}")
	public GetMainEventListResponse getEventListResponse(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) throws Exception {
		List<EventVO> list = service.getListByDate(date);
		return new GetMainEventListResponse(list);
	}
	
	@PostMapping("/addFav")
	public ResponseEntity<String> addFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.addFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("Favorite event added successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add favorite event");
		}
	}
	
	@PostMapping("/deleteFav")
	public ResponseEntity<String> deleteFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.deleteFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("Favorite event deleted successfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete favorite event");
		}
	}
	
	@PostMapping("/checkFav")
	public ResponseEntity<String> checkFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.checkFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("check");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed");
		}
		
	}
	
}
