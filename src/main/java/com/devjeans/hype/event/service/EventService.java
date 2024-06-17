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
import com.devjeans.hype.event.domain.EventVO;

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
 * </pre>
 */

public interface EventService {
	
	List<EventVO> getListTopView() throws Exception;
	
	List<EventVO> getListByDate(Date date)throws Exception;
	
	List<BannerVO> getListBanner() throws Exception;
	
	boolean addFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	boolean deleteFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
	boolean checkFavoriteEvent(Long memberId, Long eventId) throws Exception;
	
}
