package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.mapper.EventMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
	
	private EventMapper mapper;

	@Override
	public List<EventVO> getListTopView() {
		
		log.info("조회수 top10 조회");
		return mapper.getTopViewEvents();
	}

	@Override
	public List<EventVO> getListByDate(Date date) {
		
		log.info("날짜별 조회");
		return mapper.getEventsByDate(date);
	}

	@Override
	public List<BannerVO> getListBanner() {
		log.info("배너 조회");
		return mapper.getBannerEvents();
	}

	@Override
	public boolean addFavoriteEvent(Long memberId, Long eventId) {
	
		log.info("즐겨찾기 추가");
		log.info("memberId: " + memberId);
		log.info("eventId: " + eventId);
		
		return mapper.insertFavorite(memberId, eventId) == 1;
	}

	@Override
	public boolean deleteFavoriteEvent(Long memberId, Long eventId) {
		log.info("즐겨찾기 제거");
		log.info("memberId: " + memberId);
		log.info("eventId: " + eventId);
		
		return mapper.deleteFavorite(memberId, eventId) == 1;
	}

	@Override
	public boolean checkFavoriteEvent(Long memberId, Long eventId) {
		log.info("즐겨찾기 여부 확인");
		log.info(mapper.getCheckFavorite(memberId, eventId));
		return mapper.getCheckFavorite(memberId, eventId) == 1;
	}

}
