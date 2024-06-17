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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/*-context.xml")
@Log4j
public class EventServiceTests {
	
	@Autowired
	private EventService service;
	
	@Test
	public void testGetListTopView() {
		service.getListTopView().forEach(event->log.info(event));
	}
	
	@Test
	public void testGetListByDate() {
		Date date = new Date();
				
		service.getListByDate(date).forEach(event->log.info(event));
	}
	
	@Test
	public void testGetListBanner() {
		
		service.getListBanner().forEach(event->log.info(event));;

	}
	
	@Test
	public void testAddFavoriteEvent() {
		
		boolean result = service.addFavoriteEvent(3L, 1L);
		
		assertTrue(result);
		
	}
	
	@Test
	public void testDeleteFavoriteEvent() {
		
		boolean result = service.deleteFavoriteEvent(3L, 1L);
		
		assertTrue(result);
		
	}
	
	@Test
	public void testCheckFavoriteEvent() {
		
		boolean result = service.checkFavoriteEvent(3L, 1L);
		
		assertTrue(result);
	}
	
}
