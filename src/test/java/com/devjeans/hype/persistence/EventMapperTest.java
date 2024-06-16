package com.devjeans.hype.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.event.mapper.EventMapper;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:**/*-context.xml")
@Log4j
public class EventMapperTest {

	@Autowired
	private EventMapper mapper;
	
	@Test
	public void testGetDual() {
		mapper.getDual().forEach((row) -> {
			log.info(row);
			assertEquals("X", row.toString());
			});
		
		Object result = mapper.getDual2();
		assertEquals("X", result.toString());
	}
}
