package com.devjeans.hype.event.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.AdminCreateEventRequest;
import com.devjeans.hype.event.dto.AdminGetEventDetailResponse;
import com.devjeans.hype.event.dto.AdminGetEventListResponse;
import com.devjeans.hype.event.dto.AdminModifyEventRequest;
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
	
	/**
	 * 행사 리스트 요약 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public AdminGetEventListResponse getEventList(Criteria cri) throws Exception {
		List<EventVO> eventList = service.getEventListWithPaging(cri);
		
		return new AdminGetEventListResponse(eventList);
	}
	
	/**
	 * 행사 디테일 조회
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/detail/{eventId}")
	public AdminGetEventDetailResponse getEventDetail(@PathVariable("eventId") Long eventId) throws Exception{
		EventVO event = service.getEventById(eventId);
		
		return new AdminGetEventDetailResponse(event);
	}
	
	/**
	 * 행사 추가
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PostMapping("")
	public ResponseEntity<String> createEvent(@RequestBody @Valid AdminCreateEventRequest request,
			BindingResult bs) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		
		return service.createEvent(request.toEventVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 행사 수정
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PutMapping("")
	public ResponseEntity<String> modifyEvent(@RequestBody @Valid AdminModifyEventRequest request,
			BindingResult bs) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.modifyEvent(request.toEventVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{eventId}")
	public ResponseEntity<String> modifyEvent(@PathVariable("eventId") Long eventId) throws Exception {
		
		return service.removeEvent(eventId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
}
