package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.mapper.EventMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 메인페이지 행사조회 서비스 구현체
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
@Log4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
	
	private EventMapper mapper;

	/**
	 * 조회수 높은 순 행사 리스트 조회 
	 */
	@Override
	public List<EventVO> getListTopView() throws Exception {
		
		return mapper.getTopViewEvents();
	}

	/**
	 * 날짜별 행사 조회  
	 */
	@Override
	public List<EventVO> getListByDate(Date date) throws Exception {
		
		return mapper.getEventsByDate(date);
	}

	/**
	 * 배너 행사 조회
	 */
	@Override
	public List<BannerVO> getListBanner() throws Exception {

		return mapper.getBannerEvents();
	}

	/**
	 * 행사 즐겨찾기 추가
	 */
	@Override
	public boolean addFavoriteEvent(Long memberId, Long eventId) throws Exception {
	
		return mapper.insertFavorite(memberId, eventId) == 1;
	}

	/**
	 * 행사 즐겨찾기 제외
	 */
	@Override
	public boolean deleteFavoriteEvent(Long memberId, Long eventId) throws Exception {
		
		return mapper.deleteFavorite(memberId, eventId) == 1;
	}

	/**
	 * 행사 즐겨찾기 여부 확인
	 */
	@Override
	public boolean checkFavoriteEvent(Long memberId, Long eventId) throws Exception {

		return mapper.getCheckFavorite(memberId, eventId) == 1;
	}

}
