package com.devjeans.hype.event.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;

public interface EventService {
	
	public List<EventVO> getListTopView();
	
	public List<EventVO> getListByDate(Date date);
	
	public List<BannerVO> getListBanner();
	
	public boolean addFavoriteEvent(Long memberId, Long eventId);
	
	public boolean deleteFavoriteEvent(Long memberId, Long eventId);
	
	public boolean checkFavoriteEvent(Long memberId, Long eventId);
	
}
