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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devjeans.hype.aop.Auth;
import com.devjeans.hype.aop.Auth.Role;
import com.devjeans.hype.event.domain.BranchVO;
import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventTypeVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;
import com.devjeans.hype.event.dto.AdminCreateBannerRequest;
import com.devjeans.hype.event.dto.AdminCreateCategoryRequest;
import com.devjeans.hype.event.dto.AdminCreateEventHashtagRequest;
import com.devjeans.hype.event.dto.AdminCreateEventRequest;
import com.devjeans.hype.event.dto.AdminCreateHashtagRequest;
import com.devjeans.hype.event.dto.AdminGetBranchListResponse;
import com.devjeans.hype.event.dto.AdminGetCategoryListResponse;
import com.devjeans.hype.event.dto.AdminGetEventDetailResponse;
import com.devjeans.hype.event.dto.AdminGetEventHashtagListResponse;
import com.devjeans.hype.event.dto.AdminGetEventListResponse;
import com.devjeans.hype.event.dto.AdminGetEventListSummaryResponse;
import com.devjeans.hype.event.dto.AdminGetEventTypeListResponse;
import com.devjeans.hype.event.dto.AdminGetHashtagListResponse;
import com.devjeans.hype.event.dto.AdminModifyBannerOrderRequest;
import com.devjeans.hype.event.dto.AdminModifyCategoryRequest;
import com.devjeans.hype.event.dto.AdminModifyEventRequest;
import com.devjeans.hype.event.dto.AdminModifyHashtagRequest;
import com.devjeans.hype.event.service.AdminEventService;

import lombok.RequiredArgsConstructor;
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
 * 2024.06.18  	조영욱        카테고리, 해시태그, 이벤트-해시태그 추가
 * 2024.06.19  	조영욱        이벤트 타입, 카테고리, 해시태그 리스트 조회 추가
 * 2024.06.19  	조영욱        이미지 업로드 추가
 * 2024.06.20  	조영욱        배너 CRUD 추가
 * 2024.06.21  	조영욱        행사 조회에 페이지네이션 출력 후 더 출력할 행사 있는지 반환 추가
 * 2024.06.22   조영욱        Interceptor를 통해 클래스 전체 ADMIN Role 유효성 검사
 * </pre>
 */

@Auth(role = Role.ADMIN)
@RestController
@RequestMapping(
		value="/admin/event",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@RequiredArgsConstructor
public class AdminEventController {

	private final AdminEventService service;
	
	// Event 시작
	/**
	 * 행사 리스트 페이지네이션 조회
	 * @param cri
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list")
	public AdminGetEventListResponse getEventList(Criteria cri) throws Exception {
		List<EventVO> eventList = service.getEventListWithPaging(cri);
		boolean isNextEventExist = service.isNextEventExist(cri);
		return new AdminGetEventListResponse(eventList, isNextEventExist);
	}
	
	/**
	 * 행사 리스트 요약 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/list/summary")
	public AdminGetEventListSummaryResponse getEventSummaryList() throws Exception {
		List<EventVO> eventList = service.getEventListSummary();
		return new AdminGetEventListSummaryResponse(eventList);
	}
	
	/**
	 * 행사 디테일 조회
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/detail/{eventId}")
	public AdminGetEventDetailResponse getEventDetail(
			@PathVariable("eventId") Long eventId) throws Exception{
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
	@PostMapping(value = "",consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> createEvent(
			@RequestPart("request") @Valid AdminCreateEventRequest request,
			BindingResult bs,
			@RequestPart("file") MultipartFile file
			) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		
		return service.createEvent(request.toEventVO(), file)
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
	@PutMapping(value = "",consumes = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> modifyEvent(
			@RequestPart("request") @Valid AdminModifyEventRequest request,
			BindingResult bs,
			@RequestPart(value="file", required=false) MultipartFile file) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.modifyEvent(request.toEventVO(), file)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 행사 삭제
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/{eventId}")
	public ResponseEntity<String> modifyEvent(
			@PathVariable("eventId") Long eventId) throws Exception {
//		throw new CustomException(null);
		
		return service.removeEvent(eventId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	// Event 끝
	// **********************************************
	// Category 시작
	
	/**
	 * 카테고리 전체 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/category/list")
	public AdminGetCategoryListResponse getCategoryList() throws Exception {
		List<CategoryVO> categoryList = service.getCategoryList();
		
		log.info(categoryList);
		
		return new AdminGetCategoryListResponse(categoryList);
	}
	
	/**
	 * 카테고리 생성
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/category")
	public ResponseEntity<String> createCategory(
			@RequestBody @Valid AdminCreateCategoryRequest request,
			BindingResult bs) throws Exception {
			
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.createCategory(request.toCategoryVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	
	/**
	 * 카테고리 수정
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/category")
	public ResponseEntity<String> modifyCategory(
			@RequestBody @Valid AdminModifyCategoryRequest request,
			BindingResult bs) throws Exception {
			
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.modifyCategory(request.toCategoryVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	

	/**
	 * 카테고리 삭제
	 * @param categoryId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<String> modifyCategory(
			@PathVariable("categoryId") Long categoryId) throws Exception {
		
		return service.removeCategory(categoryId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	// Category 끝
	// **********************************************
	// Hashtag 시작
	
	/**
	 * 해시태그 전체 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/hashtag/list")
	public AdminGetHashtagListResponse getHashtagList() throws Exception {
		List<HashtagVO> hashtagList = service.getHashtagList();
		
		log.info(hashtagList);
		
		return new AdminGetHashtagListResponse(hashtagList);
	}
	
	/**
	 * 해시태그 생성
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/hashtag")
	public ResponseEntity<String> createHashtag(
			@RequestBody @Valid AdminCreateHashtagRequest request,
			BindingResult bs) throws Exception {
			
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.createHashtag(request.toHashtagVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	
	/**
	 * 해시태그 수정
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/hashtag")
	public ResponseEntity<String> modifyHashtag(
			@RequestBody @Valid AdminModifyHashtagRequest request,
			BindingResult bs) throws Exception {
			
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.modifyHashtag(request.toHashtagVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	

	/**
	 * 해시태그 삭제
	 * @param hashtagId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/hashtag/{hashtagId}")
	public ResponseEntity<String> removeHashtag(
			@PathVariable("hashtagId") Long hashtagId) throws Exception {
		
		return service.removeHashtag(hashtagId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	// Hashtag 끝
	// **********************************************
	// EventHashtag 시작
	
	/**
	 * 이벤트에 달린 해시태그 리스트 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/event-hashtag/list/{eventId}")
	public AdminGetEventHashtagListResponse getHashtagListByEventId(
			@PathVariable("eventId") Long eventId) throws Exception {
		List<EventHashtagVO> eventHashtagList = service.getEventHashtagListByEventId(eventId);
		
		log.info(eventHashtagList);
		
		return new AdminGetEventHashtagListResponse(eventHashtagList);
	}
	
	/**
	 * 이벤트에 해시태그 연결 추가
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/event-hashtag")
	public ResponseEntity<String> createEventHashtag(
			@RequestBody @Valid AdminCreateEventHashtagRequest request,
			BindingResult bs) throws Exception {
			
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.createEventHashtag(request.toEventHashtagVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	
	/**
	 * 이벤트에 연결되어있는 해시태그 연결 해제
	 * @param eventId
	 * @param hashtagId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/event-hashtag/{eventId}/{hashtagId}")
	public ResponseEntity<String> removeEventHashtag(
			@PathVariable("eventId") Long eventId,
			@PathVariable("hashtagId") Long hashtagId) throws Exception {
		
		return service.removeEventHashtag(eventId, hashtagId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	// EventHashtag 끝
	// **********************************************
	// 리스트 조회 시작
	
	/**
	 * 행사 타입 리스트 전체 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/type/list")
	public AdminGetEventTypeListResponse getEventTypeList() throws Exception {
		List<EventTypeVO> eventTypeList = service.getEventTypeList();
		
		log.info(eventTypeList);
		
		return new AdminGetEventTypeListResponse(eventTypeList);
	}
	
	/**
	 * 지점 리스트 전체 조회
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/branch/list")
	public AdminGetBranchListResponse getBranchList() throws Exception {
		List<BranchVO> branchList = service.getBranchList();
		
		log.info(branchList);
		
		return new AdminGetBranchListResponse(branchList);
	}
	
	// 리스트 조회 끝
	// **********************************************
	// Banner 시작
	
	/**
	 * 배너에 출력할 이벤트 설정
	 * @param request
	 * @param bs
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/banner")
	public ResponseEntity<String> createBanner(
			@RequestBody @Valid AdminCreateBannerRequest request,
			BindingResult bs) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}

		return service.createBanner(request.toBannerVO())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);		
	}
	
	/**
	 * 배너에 출력할 이벤트 해제
	 * @param eventId
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/banner/{eventId}")
	public ResponseEntity<String> removeBanner(
			@PathVariable("eventId") Long eventId) throws Exception {
		
		return service.removeBanner(eventId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/banner/order")
	public ResponseEntity<String> modifyBannerOrder(
			@RequestBody @Valid AdminModifyBannerOrderRequest request,
			BindingResult bs) throws Exception {
		if (bs.hasErrors()) {
			log.info(bs);
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
		
		return service.modifyBannerOrder(request.getBannerList())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
}
