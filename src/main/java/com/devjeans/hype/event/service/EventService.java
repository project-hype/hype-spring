package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

/**
 * 메인페이지 행사 서비스 인터페이스
 * @author 정은지 
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
 * </pre>
 */

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.StarScoreVO;
import com.devjeans.hype.event.dto.EventFilterRequest;

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
 * 2024.06.21   정은지        별점 작성 기능 추가
 * 2024.06.21   조영욱        이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * </pre>
 */

public interface EventService {
	
	List<EventVO> getListTopView(Long memberId) throws Exception;
	
	List<EventVO> getListByDate(Date date, Long memberId) throws Exception;
	
	List<BannerVO> getListBanner() throws Exception;
	
	boolean addFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	boolean deleteFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	List<Long> getMyFavoriteEvent(Long memberId) throws Exception;
	
	List<EventVO> getEventDetail(Long eventId) throws Exception;
	
	List<Double> getEventStarScore(Long eventId) throws Exception;
	
	List<EventHashtagVO> getEventHashtagList(Long eventId) throws Exception;
	
	int getEventFavoriteCount(Long eventId) throws Exception;
	
	boolean getEventFavoriteStatus(Long memberId, Long eventId) throws Exception;
	
	boolean addEventStarScore(StarScoreVO starScore) throws Exception;
	
	List<EventVO> getListWithFilter(EventFilterRequest dto) throws Exception;
	
	boolean isNextEventExist(EventFilterRequest dto) throws Exception;
}
