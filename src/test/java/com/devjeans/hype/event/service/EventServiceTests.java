package com.devjeans.hype.event.service;

import java.text.SimpleDateFormat;
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
		service.getListTopView().forEach(event -> log.info(event));
	}
	
	@Test
	public void testGetListByDate() {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd"); 
		Date date = new Date();
		
		String now = sdf1.format(date);
		
		log.info(now);
		service.getListByDate(date).forEach(event -> log.info(event));
	}
	
	
}
