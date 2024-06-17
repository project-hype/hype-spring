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
 * @author 정은
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
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
			service.getListTopView().forEach(event->log.info(event));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetListByDate() {
		Date date = new Date();
				
		try {
			service.getListByDate(date).forEach(event->log.info(event));
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
			boolean result = service.checkFavoriteEvent(3L, 1L);
			assertTrue(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
