package com.devjeans.hype.persistence;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.mapper.AdminEventMapper;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:**/*-context.xml")
@Log4j
public class AdminEventMapperTest {

	@Autowired
	private AdminEventMapper mapper;
	
	@Test
	public void testSelectEventById() {
		EventVO event = mapper.selectEventById(59L);
		
		log.info(event);
		
		assertEquals(59L, event.getEventId().longValue());
	}
	
	@Test
	public void testInsertEvent() {
		EventVO event = new EventVO();
		event.setBranchId(1L);
		event.setEventTypeId(1L);
		event.setCategoryId(1L);
		event.setTitle("가나다라");
		event.setContent("테스트콘텐츠");
		event.setImageUrl("https://testURL");
		event.setStartDate(new Date());
		event.setEndDate(new Date());
		event.setDetailAddress("3층 해당 매장");
		
		int result = mapper.insertEvent(event);
		
		assertEquals(1, result);
	}
	
	@Test
	public void testUpdateEvent() {
		EventVO event = mapper.selectEventById(64L);
		if (event == null) {
			return;
		}
		
		event.setBranchId(2L);
		event.setEventTypeId(2L);
		event.setCategoryId(2L);
		event.setTitle("수정234 가나다라");
		event.setContent("수정 테스트콘텐츠");
		event.setImageUrl("https://sujung.testURL");
		
		String sdate = "20241215";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		
		try {
			event.setEndDate(format.parse(sdate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		event.setDetailAddress("3수정층 해당 매장");
		
		int result = mapper.updateEvent(event);
		
		assertEquals(1, result);
	}
	
	@Test
	public void testDeleteEvent() {
		int result = mapper.deleteEvent(62L);
		
		log.info(result);
		
		assertEquals(1, result);
	}
}
