package com.devjeans.hype.event.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.devjeans.hype.aop.Auth;
import com.devjeans.hype.aop.LoginId;
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
 * 2024.06.17   정은지        최초 생성
 * 2024.06.19   정은지        행사 상세 조회 추가
 * 2024.06.20   조영욱        이벤트 필터로 조회 추가
 * 2024.06.21   조영욱        이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * 2024.06.21   정은지        조회수 증가, 별점순 조회, 유사한 이벤트 조회, 사용자 별점 조회 추가
 * 2024.06.21   조영욱        LoginId 어노테이션 적용
 * 2024.06.22   정은지        별점 추가/수정/삭제 프로시저 호출 기능 추가 
 * 2024.06.22   조영욱        개인 별 추천 행사 조회 추가
 * </pre>
 */

@RestController
@RequestMapping(value="/event", produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class EventController {
	
	@Autowired
	private EventService service;
	
	/**
	 * 조회수 순 이벤트 조회 
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/top")
	public GetEventListResponse getListTopViewWithFavorite(@LoginId Long memberId) throws Exception {
		List<EventVO> list = service.getListTopView(memberId);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(memberId);
		return new GetEventListResponse(list, favoriteEventIds);
	}
	
	/**
	 * 배너 이벤트 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/banner")
	public GetBannerEventListResponse getListBanner() throws Exception {
		List<BannerVO> list = service.getListBanner();
		
		return new GetBannerEventListResponse(list);
	}
	
	/**
	 * 날짜별 이벤트 조회
	 * @param date
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/{date}")
	public GetEventListResponse getEventListResponse(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
													@LoginId Long memberId) throws Exception {
		List<EventVO> list = service.getListByDate(date, memberId);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(memberId);
		return new GetEventListResponse(list, favoriteEventIds);
	}
	
	/**
	 * 즐겨찾기 추가
	 * @param request
	 * @return
	 */
	@Auth
	@PostMapping("/favorite")
	public ResponseEntity<String> addFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.addFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
		}
	}
	
	/**
	 * 즐겨찾기 제거 
	 * @param request
	 * @return
	 */
	@Auth
	@DeleteMapping("/favorite")
	public ResponseEntity<String> deleteFavoriteEvent(@RequestBody CreateFavoriteEventRequest request) {
		try {
			service.deleteFavoriteEvent(request.getMemberId(), request.getEventId());
			return ResponseEntity.ok("success");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
		}
	}
	
	/**
	 * 행사 상세 조회
	 * @param eventId
	 * @param memberId
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/{eventId}")
	public GetEventDetailResponse getEventDetail(@PathVariable("eventId") Long eventId, 
												@LoginId Long memberId,
												HttpServletRequest request,
										        HttpServletResponse response) throws Exception {
		
	    // 기존 쿠키 확인
	    Cookie[] cookies = request.getCookies();
	    boolean viewed = false;

	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if (cookie.getName().equals("event_" + eventId)) {
	                viewed = true;
	                break;
	            }
	        }
	    }

	    // 조회수 증가
	    if (!viewed) {
	        service.plusViewCount(eventId);

	        // 새로운 쿠키 생성하여 설정
	        Cookie cookie = new Cookie("event_" + eventId, "viewed");
	        cookie.setMaxAge(24 * 60 * 60); // 24시간 후 만료
	        cookie.setPath("/");
	        response.addCookie(cookie);
	    }
		
		List<EventVO> event = service.getEventDetail(eventId);
		List<EventHashtagVO> hashtags = service.getEventHashtagList(eventId);
		List<Double> scores = service.getEventStarScore(eventId);
        int favoriteCount = service.getEventFavoriteCount(eventId);
        boolean isFavorite = service.getEventFavoriteStatus(memberId, eventId);
        Double myScore = service.getMyEventScore(memberId, eventId);

		return new GetEventDetailResponse(event, hashtags, scores, favoriteCount, isFavorite, myScore);
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
	
	/**
	 * 별점 많은 순 이벤트 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/score")
	public GetTopScoreCountEventResponse getTopScoreCountEvents() throws Exception{
		
		List<EventVO> list = service.getTopScoreCountEvents();
		return new GetTopScoreCountEventResponse(list);
	}
	
	/**
	 * 유사한 이벤트 조회 
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/similar/{eventId}")
	public GetEventListResponse getSimilarEvents(@PathVariable("eventId") Long eventId) throws Exception {
		
		List<EventVO> list = service.getSimilarEvents(eventId);
		return new GetEventListResponse(list);
	}
	
	/**
	 * 별점 추가/수정/삭제 
	 * @param request
	 * @throws Exception
	 */
	@Auth
	@PostMapping("/score")
	public void manageStarScore(@RequestBody StarScoreRequest request) throws Exception {
		
		service.manageStarScore(request);
	}
	
	/**
	 * 개인 별 추천 행사 조회
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/recommend")
	public GetEventListResponse getRecommendEventList(
			@LoginId Long memberId) throws Exception {
		List<EventVO> list = service.getRecommendEventList(memberId);
		List<Long> favoriteEventIds = service.getMyFavoriteEvent(memberId);
		return new GetEventListResponse(list, favoriteEventIds);
	}

}
