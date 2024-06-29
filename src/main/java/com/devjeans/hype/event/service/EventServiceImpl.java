package com.devjeans.hype.event.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.StarScoreVO;
import com.devjeans.hype.event.dto.EventFilterRequest;
import com.devjeans.hype.event.dto.StarScoreRequest;
import com.devjeans.hype.event.mapper.EventMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.log4j.Log4j;

/**
 * 행사 관련 서비스 구현체
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
 * 2024.06.21   조영욱        이벤트 검색,필터 조회에 페이지네이션 적용, 카테고리/해시태그 검색 추가
 * 2024.06.21   정은지        조회수 증가, 별점순 조회, 유사한 이벤트 조회, 사용자 별점 조회 추가
 * 2024.06.22   정은지        별점 추가/수정/삭제 프로시저 호출 기능 추가 
 * 2024.06.22   조영욱        개인 별 추천 행사 조회 추가
 * </pre>
 */
@Log4j
@Service
public class EventServiceImpl implements EventService {
	
	private final EventMapper mapper;
	private String cfServerUrl;
	
	public EventServiceImpl(EventMapper mapper) throws Exception {
		this.mapper = mapper;
    	Properties properties = new Properties();
		properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
		cfServerUrl = properties.getProperty("cf_server_url");
    }

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
	@Transactional
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
	public void plusViewCount(Long eventId) throws Exception {
		
		mapper.updateViewCount(eventId);
	}

	/**
	 * 유사한 이벤트 조회
	 */
	@Override
	public List<EventVO> getSimilarEvents(Long eventId) throws Exception {
		
		return mapper.getSimilarEvents(eventId);
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
	public void manageStarScore(StarScoreRequest dto) throws Exception {	
		
		mapper.callManageStarScoreProcedure(dto);
		// 추천 서버 데이터셋 트레이닝 요청
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(cfServerUrl + "train");
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
		
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		httpClient.execute(request, responseHandler);
	}
	
	/**
	 * 개인 별 추천 행사 조회 메소드
	 * 추천 조회 서버에 memberId로 요청을 보내 개인 별 추천 행사를 받아온다.
	 */
	@Override
	public List<EventVO> getRecommendEventList(Long memberId) throws Exception {

		// error 방지
		if (memberId == null) memberId = (long) 0;
		// 추천 서버로 API 호출
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(cfServerUrl + memberId);
		request.setHeader(HttpHeaders.CONTENT_TYPE, "application/json;charset=UTF-8");
		
		
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		try {
		String response = httpClient.execute(request, responseHandler);
		
		ObjectMapper objectMapper = new ObjectMapper();
		// 자바스크립트의 number는 Integer로 변환됨
        List<Integer> integerList = objectMapper.readValue(response, ArrayList.class);
        
        // 명시적 Long type 캐스팅
        List<Long> list = new ArrayList<>();
        for (Integer intValue : integerList) {
        	list.add((long) intValue);
        }
       
        // IN 구문으로 인하여 순서가 보장되지 않는다.
        List<EventVO> result = mapper.getEventsByIdList(list);
        
        // 추천 순서에 맞게 결과를 정렬한다.
        // eventId를 key로 갖고, EventVO를 value로 갖는 HashMap 생성
        Map<Long, EventVO> eventMap = new HashMap<>();
        for (EventVO event : result) {
            eventMap.put(event.getEventId(), event);
        }

        // list의 순서대로 정렬된 EventVO 객체 리스트 생성
        List<EventVO> sortedResult = new ArrayList<>();
        for (Long eventId : list) {
            sortedResult.add(eventMap.get(eventId));
        }
        
        return sortedResult;
		} catch (Exception e) {
			// 추천 서버 문제로 오류 발생 시 그 날 진행중인 행사 리스트 반환
			return this.getListByDate(new Date(), memberId);
		}
        
	}

}
