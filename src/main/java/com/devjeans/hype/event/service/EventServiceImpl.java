package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.StarScoreVO;
import com.devjeans.hype.event.dto.EventFilterRequest;
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
 * 2024.06.19   정은지        행사 상세 조회 추가
 * 2024.06.20   조영욱        행사 리스트 필터로 조회 추가
 * 2024.06.21   정은지        별점 작성 기능 추가
 * 2024.06.21   조영욱        이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * 2024.06.21   정은지  		유사한 행사 조회 추가
 * 2024.06.22   정은지        별점 추가/수정/삭제 프로시저 호출 기능 추가 
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
	public List<EventVO> getListTopView(Long memberId) throws Exception {
	
		return mapper.getTopViewEvents();
	}

	/**
	 * 날짜별 행사 조회  
	 */
	@Override
	public List<EventVO> getListByDate(Date date, Long memberId) throws Exception {
		
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
	 * 행사 즐겨찾기 삭제
	 */
	@Override
	public boolean deleteFavoriteEvent(Long memberId, Long eventId) throws Exception {
		
		return mapper.deleteFavorite(memberId, eventId) == 1;
	}

	/**
	 * 내가 즐겨찾기한 행사 조회 
	 */
	@Override
	public List<Long> getMyFavoriteEvent(Long memberId) throws Exception {
		
		return mapper.getMyFavoriteEvent(memberId);
	}

	/**
	 * 행사 상세 조회
	 */
	@Override
	public List<EventVO> getEventDetail(Long eventId) throws Exception {

		return mapper.getEventDetail(eventId);
	}

	/**
	 * 행사 별점 조회
	 */
	@Override
	public List<Double> getEventStarScore(Long eventId) throws Exception {
		
		return mapper.getEventStarScore(eventId);
	}

	/**
	 * 행사 해시태그 리스트 조회
	 */
	@Override
	public List<EventHashtagVO> getEventHashtagList(Long eventId) throws Exception {
		
		return mapper.getEventHashtagList(eventId);
	}

	/**
	 * 행사 즐겨찾기 카운트 조회
	 */
	@Override
	public int getEventFavoriteCount(Long eventId) throws Exception {

		return mapper.getEventFavoriteCount(eventId);
	}

	/**
	 * 행사 즐겨찾기 여부 조회 
	 */
	@Override
	public boolean getEventFavoriteStatus(Long memberId, Long eventId) throws Exception {

		return mapper.getEventFavoriteStatus(memberId, eventId);
	}
	
	
	/**
	 * 행사 리스트 필터로 조회 
	 */
	@Override
	public List<EventVO> getListWithFilter(EventFilterRequest dto) throws Exception {
		return mapper.getEventWithFilter(dto);
	}

	/**
	 * 별점 추가
	 */
	@Override
	public boolean addEventStarScore(StarScoreVO starScore) throws Exception {

		return mapper.insertStarScore(starScore) == 1;
	}
	
	/**
	 * 다음 이벤트가 존재하는지 반환
	 * 페이지네이션을 위한 메소드
	 */
	@Override
	public boolean isNextEventExist(EventFilterRequest dto) throws Exception {
		return mapper.selectNextEvent(dto) != null ? true : false;
	}

	/**
	 * 별점 많은 순 행사 리스트 조회
	 */
	@Override
	public List<EventVO> getTopScoreCountEvents() throws Exception {
		
		return mapper.getTopScoreCountEvents();
	}

	/**
	 * 조회수 증가 
	 */
	@Override
	public boolean plusViewCount(Long eventId) throws Exception {
		
		return mapper.updateViewCount(eventId) == 1;
	}

	/**
	 * 유사한 이벤트 조회
	 */
	@Override
	public List<EventVO> getLikeEvents(Long eventId) throws Exception {
		
		return mapper.getLikeEvents(eventId);
	}

	/**
	 * 회원 이벤트 별점 조회 
	 */
	@Override
	public Double getMyEventScore(Long memberId, Long eventId) throws Exception {

		return mapper.getMyEventScore(memberId, eventId);
	}

	/**
	 * 별점 추가/수정/삭제 프로시저 호출
	 */
	@Override
	public void manageStarScore(Long eventId, Long memberId, String action, Double score) throws Exception {
		mapper.callManageStarProcedure(eventId, memberId, action, score);
		
	}

}
