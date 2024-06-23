package com.devjeans.hype.event.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.CreateFavoriteEventRequest;
import com.devjeans.hype.event.dto.CreateStarScoreRequest;
import com.devjeans.hype.event.dto.EventFilterRequest;
import com.devjeans.hype.event.dto.GetBannerEventListResponse;
import com.devjeans.hype.event.dto.GetEventDetailResponse;
import com.devjeans.hype.event.dto.GetEventListResponse;
import com.devjeans.hype.event.dto.GetTopScoreCountEventResponse;
import com.devjeans.hype.event.dto.StarScoreRequest;
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
 * 2024.06.20   조영욱         이벤트 필터로 조회 추가
 * 2024.06.21   조영욱         이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * 2024.06.22   정은지 		  
 * </pre>
 */

@RestController
@RequestMapping(value="/event", produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class EventController {
	
	@Autowired
	private EventService service;
	
	@GetMapping("/list/top")
	public GetEventListResponse getListTopViewWithFavorite(@RequestParam(required = false) Long memberId) throws Exception {
		List<EventVO> list = service.getListTopView(memberId);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(memberId);
		return new GetEventListResponse(list, favoriteEventIds);
	}
	
	@GetMapping("/list/banner")
	public GetBannerEventListResponse getListBanner() throws Exception {
		List<BannerVO> list = service.getListBanner();
		
		return new GetBannerEventListResponse(list);
	}
	
	@GetMapping("/list/{date}")
	public GetEventListResponse getEventListResponse(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @RequestParam(required = false) Long memberId) throws Exception {
		List<EventVO> list = service.getListByDate(date, memberId);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(memberId);
		return new GetEventListResponse(list, favoriteEventIds);
	}
	
	@PostMapping("/addFav")
	public ResponseEntity<String> addFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.addFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
		}
	}
	
	@DeleteMapping("/deleteFav")
	public ResponseEntity<String> deleteFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.deleteFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
		}
	}
	
	@GetMapping("/{eventId}")
	@Transactional
	public GetEventDetailResponse getEventDetail(@PathVariable("eventId") Long eventId, @RequestParam(required = false) Long memberId) throws Exception {
		List<EventVO> event = service.getEventDetail(eventId);
		List<EventHashtagVO> hashtags = service.getEventHashtagList(eventId);
		List<Double> scores = service.getEventStarScore(eventId);
        int favoriteCount = service.getEventFavoriteCount(eventId);
        boolean isFavorite = service.getEventFavoriteStatus(memberId, eventId);
        Double myScore = service.getMyEventScore(memberId, eventId);
        
        if (service.plusViewCount(eventId)) {
    		return new GetEventDetailResponse(event, hashtags, scores, favoriteCount, isFavorite, myScore);
        };
        
		return new GetEventDetailResponse(event, hashtags, scores, favoriteCount, isFavorite, myScore);
	}
	
	@PostMapping("/starScore")
	public ResponseEntity<String> addEventStarScore(@RequestBody CreateStarScoreRequest request,
					BindingResult bs) throws Exception {
		
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		
		return service.addEventStarScore(request.toStarScoreVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		

	}
	
	@PostMapping("/list/filter")
	public GetEventListResponse getListWithFilter(
			@RequestBody @Valid EventFilterRequest request,
			BindingResult bs) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return null;
		}
		
		List<EventVO> list = service.getListWithFilter(request);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(null);
		boolean isNextEventExist = service.isNextEventExist(request);
		return new GetEventListResponse(list, favoriteEventIds, isNextEventExist);
	}
	
	@GetMapping("/list/score")
	public GetTopScoreCountEventResponse getTopScoreCountEvents() throws Exception{
		
		List<EventVO> list = service.getTopScoreCountEvents();
		return new GetTopScoreCountEventResponse(list);
	}
	
	@GetMapping("/list/like/{eventId}")
	public GetEventListResponse getLikeEvents(@PathVariable("eventId") Long eventId) throws Exception {
		
		List<EventVO> list = service.getLikeEvents(eventId);
		return new GetEventListResponse(list);
	}
	
	@PostMapping("/score")
	public void manageStarScore(@RequestBody StarScoreRequest request) throws Exception {
		
		service.manageStarScore(request.eventId, request.memberId, request.action, request.score);
		
	}
	

}
