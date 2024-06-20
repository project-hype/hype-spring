package com.devjeans.hype.event.service;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

/**
 * 메인페이지 행사 서비스 테스트
 * @author 정은지
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
 * 2024.06.19   정은지        행사 상세 조회 테스트 추가
 * </pre>
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/*-context.xml")
@Log4j
public class EventServiceTests {
	
	@Autowired
	private EventService service;
	
	@Test
	public void testGetListTopView() {
		try {
			service.getListTopView(1L).forEach(event->log.info(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListByDate() {
		Date date = new Date();
				
		try {
			service.getListByDate(date, 1L).forEach(event->log.info(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListBanner() {
		
		try {
			service.getListBanner().forEach(event->log.info(event));
		} catch (Exception e) {
			e.printStackTrace();
		};

	}
	
	@Test
	public void testAddFavoriteEvent() {
		
		try {
			boolean result = service.addFavoriteEvent(3L, 1L);
			assertTrue(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void testDeleteFavoriteEvent() {
		
		try {
			boolean result = service.deleteFavoriteEvent(3L, 1L);
			assertTrue(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCheckFavoriteEvent() {
		
		try {
			service.getMyFavoriteEvent(3L).forEach(event->log.info(event));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetEventDetail() {
		
		try {
			service.getEventDetail(3L).forEach(event->log.info(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetEventStarScore() {
		
		try {
			service.getEventStarScore(1L).forEach(score->log.info(score));
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
}
