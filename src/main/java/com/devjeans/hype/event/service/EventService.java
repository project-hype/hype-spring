package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.StarScoreVO;
import com.devjeans.hype.event.dto.EventFilterRequest;
import com.devjeans.hype.event.dto.StarScoreRequest;

/**
 * 메인페이지 행사조회 서비스 인터페이스
 * @author 정은지
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
 * 2024.06.19   정은지 		이벤트 상세 조회 추가
 * 2024.06.20   조영욱        이벤트 필터로 조회 추가
 * 2024.06.21   정은지        조회수 증가, 별점순 조회, 유사한 이벤트 조회, 사용자 별점 조회 추가
 * 2024.06.21   조영욱        이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * 2024.06.22   정은지 	    별점 추가/수정/삭제 프로시저 호출 추가
 * 2024.06.22   조영욱        개인 별 추천 행사 조회 추가
 * </pre>
 */

public interface EventService {
	
	// 조회수 순 이벤트 조회 
	List<EventVO> getListTopView(Long memberId) throws Exception;
	
	// 날짜별 이벤트 조회 
	List<EventVO> getListByDate(Date date, Long memberId) throws Exception;
	
	// 배너 이벤트 조회 
	List<BannerVO> getListBanner() throws Exception;
	
	// 즐겨찾기 추가 
	boolean addFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	// 즐겨찾기 제거
	boolean deleteFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	// 즐겨찾기 이벤트 ID 조회
	List<Long> getMyFavoriteEvent(Long memberId) throws Exception;
	
	// 이벤트 상세 조회
	List<EventVO> getEventDetail(Long eventId) throws Exception;
	
	// 이벤트 별점 조회
	List<Double> getEventStarScore(Long eventId) throws Exception;
	
	// 이벤트 해시태그 리스트 조회
	List<EventHashtagVO> getEventHashtagList(Long eventId) throws Exception;
	
	// 이벤트 즐겨찾기 수 조회
	int getEventFavoriteCount(Long eventId) throws Exception;
	
	// 이벤트 즐겨찾기 여부 조회 
	boolean getEventFavoriteStatus(Long memberId, Long eventId) throws Exception;
		
	List<EventVO> getListWithFilter(EventFilterRequest dto) throws Exception;
	
	// 별점 많은 순 조회
	List<EventVO> getTopScoreCountEvents() throws Exception;
	
	// 조회수 증가
	void plusViewCount(Long eventId) throws Exception;

	boolean isNextEventExist(EventFilterRequest dto) throws Exception;
	
	// 유사한 이벤트 조회
	List<EventVO> getSimilarEvents(Long eventId) throws Exception;
	
	// 회원 이벤트 별점 조회
	Double getMyEventScore(Long memberId, Long eventId) throws Exception;
	
	// 별점 생성/수정/삭제 프로시저 호출
	void manageStarScore(StarScoreRequest dto) throws Exception;
	
	// 행사 아이디 리스트로 행사 조회
	List<EventVO> getRecommendEventList(Long memberId) throws Exception;

}
