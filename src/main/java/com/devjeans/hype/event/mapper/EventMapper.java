package com.devjeans.hype.event.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.StarScoreVO;
import com.devjeans.hype.event.dto.EventFilterRequest;
import com.devjeans.hype.event.dto.StarScoreRequest;

/**
 * 행사 매퍼 인터페이스
 * @author 정은지 
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
 * 2024.06.19   정은지  	    이벤트 상세 조회 추가
 * 2024.06.20   조영욱        이벤트 필터로 조회 추가, 카테고리/해시태그 검색 추가
 * 2024.06.21   정은지        조회수 증가 기능, 별점순 조회, 유사한 행사 조회, 사용자 별점 조회 추가
 * 2024.06.22   정은지 	    별점 추가/수정/삭제 프로시저 호출 추가
 * 2024.06.22   조영욱        개인 별 추천 행사 조회 추가
 * </pre>
 */
public interface EventMapper {
	
	// 조회수 순 이벤트 조회 
	public List<EventVO> getTopViewEvents();
	
	// 날짜별 이벤트 조회 
	public List<EventVO> getEventsByDate(Date date);
	
	// 배너 이벤트 조회 
	public List<BannerVO> getBannerEvents();
	
	// 즐겨찾기 추가 
	public int insertFavorite(@Param("memberId") Long memberId, @Param("eventId") Long eventId);
	
	// 즐겨찾기 제거
	public int deleteFavorite(@Param("memberId") Long memberId, @Param("eventId") Long eventId);

	// 즐겨찾기 이벤트 ID 조회
	public List<Long> getMyFavoriteEvent(Long memberId);
	
	// 이벤트 상세 조회
	public List<EventVO> getEventDetail(Long eventId);
	
	// 이벤트 별점 조회
	public List<Double> getEventStarScore(Long eventId);
	
	// 이벤트 해시태그 리스트 조회
	public List<EventHashtagVO> getEventHashtagList(Long eventId);
	
	// 이벤트 즐겨찾기 수 조회
	public int getEventFavoriteCount(Long eventId);
	
	// 이벤트 즐겨찾기 여부 조회 
	public boolean getEventFavoriteStatus(@Param("memberId") Long memberId, @Param("eventId") Long eventId);
			
	// 이벤트 필터로 조회
	public List<EventVO> getEventWithFilter(EventFilterRequest dto);
	
	// 별점 많은 순 조회
	public List<EventVO> getTopScoreCountEvents();
	
	// 조회수 증가
	public void updateViewCount(Long eventId);
	
	public Integer selectNextEvent(EventFilterRequest dto);
	
	// 유사한 이벤트 조회
	public List<EventVO> getSimilarEvents(Long eventId);
	
	// 회원 이벤트 별점 조회
	public Double getMyEventScore(@Param("memberId") Long memberId, @Param("eventId") Long eventId);
	
	// 별점 추가/수정/삭제 프로시저 호출
	public void callManageStarScoreProcedure(StarScoreRequest request);

	// 행사 아이디 리스트로 행사 조회
	public List<EventVO> getEventsByIdList(List<Long> eventIdList);
}
