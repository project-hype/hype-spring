package com.devjeans.hype.event.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;

/**
 * 메인페이지 행사 매퍼 인터페이스
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
public interface EventMapper {
	
	@Select("SELECT dummy FROM DUAL")
	public List<Object> getDual();
	public Object getDual2();

	@Select("SELECT event_id FROM EVENT")
	public List<EventVO> getList();
	
	// 조회수 순 이벤트 조회 
	public List<EventVO> getTopViewEvents();
	
	// 날짜별 이벤트 조회 
	public List<EventVO> getEventsByDate(Date date);
	
	// 배너 조회 
	public List<BannerVO> getBannerEvents();
	
	// 즐겨찾기 추가 
	public int insertFavorite(@Param("memberId") Long memberId, @Param("eventId") Long eventId);
	
	// 즐겨찾기 제거
	public int deleteFavorite(@Param("memberId") Long memberId, @Param("eventId") Long eventId);

	// 즐겨찾기 여부 확인
	public int getCheckFavorite(@Param("memberId") Long memberId, @Param("eventId") Long eventId);
}
