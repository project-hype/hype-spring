package com.devjeans.hype.persistence;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;
import com.devjeans.hype.event.mapper.AdminEventMapper;

import lombok.extern.log4j.Log4j;

/**
 * 어드민 행사관리 테스트 클래스
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * </pre>
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:**/*-context.xml")
@Log4j
public class AdminEventMapperTest {

	@Autowired
	private AdminEventMapper mapper;
	
	/*****************************
	 ****** Event CRUD Test ******
	 *****************************/
	
	/**
	 * Event ID로 조회
	 */
	@Test
	public void testSelectEventById() {
		EventVO event = mapper.selectEventById(59L);
		
		log.info(event);
		
		assertEquals(59L, event.getEventId().longValue());
	}
	
	/**
	 * Event 생성
	 */
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
	
	/**
	 * Event 수정
	 */
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
	
	/**
	 * Event 삭제
	 */
	@Test
	public void testDeleteEvent() {
		int result = mapper.deleteEvent(62L);
		
		log.info(result);
		
		assertEquals(1, result);
	}
	
	/******************************
	 ***** Category CRUD Test *****
	 *****************************/
	
	/**
	 * Category ID로 조회
	 */
	@Test
	public void testSelectCategoryById() {
		CategoryVO category = mapper.selectCategoryById(1L);
		
		log.info(category);
		
		assertEquals(1L, category.getCategoryId().longValue());
	}
	
	/**
	 * Category 생성
	 */
	@Test
	public void testInsertCategory() {
		CategoryVO category = new CategoryVO();
		category.setCategoryName("새카테고리");
		
		int result = mapper.insertCategory(category);
		
		assertEquals(1, result);
	}
	
	/**
	 * Category 수정
	 */
	@Test
	public void testUpdateCategory() {
		CategoryVO category = mapper.selectCategoryById(27L);
		if (category == null) {
			return;
		}
		
		category.setCategoryName("수정카테고리");
		
		int result = mapper.updateCategory(category);
		
		assertEquals(1, result);
	}
	
	/**
	 * Category 삭제
	 */
	@Test
	public void testDeleteCategory() {
		int result = mapper.deleteCategory(27L);
		
		log.info(result);
		
		assertEquals(1, result);
	}
	
	/******************************
	 ****  Hashtag CRUD Test  *****
	 *****************************/
	
	/**
	 * Hashtag ID로 조회
	 */
	@Test
	public void testSelectHashtagById() {
		HashtagVO hashtag = mapper.selectHashtagById(1L);
		
		log.info(hashtag);
		
		assertEquals(1L, hashtag.getHashtagId().longValue());
	}
	
	/**
	 * Hashtag 생성
	 */
	@Test
	public void testInsertHashtag() {
		HashtagVO hashtag = new HashtagVO();
		hashtag.setHashtagName("흰둥이");
		
		int result = mapper.insertHashtag(hashtag);
		
		assertEquals(1, result);
	}
	
	/**
	 * Hashtag 수정
	 */
	@Test
	public void testUpdateHashtag() {
		HashtagVO hashtag = mapper.selectHashtagById(2L);
		if (hashtag == null) {
			return;
		}
		
		hashtag.setHashtagName("수정해시태그");
		
		int result = mapper.updateHashtag(hashtag);
		
		assertEquals(1, result);
	}
	
	/**
	 * Hashtag 삭제
	 */
	@Test
	public void testDeleteHashtag() {
		int result = mapper.deleteHashtag(2L);
		
		log.info(result);
		
		assertEquals(1, result);
	}
	
	/******************************
	 ****  EventHashtag CRUD Test  *****
	 *****************************/
	
	/**
	 * EventHashtag List Event ID로 조회
	 */
	@Test
	public void testSelectEventHashtagListByEventId() {
		List<EventHashtagVO> eventHashtagList = mapper.selectEventHashtagListByEventId(1L);
		
		log.info(eventHashtagList);
		
		for (EventHashtagVO eventHashtag : eventHashtagList) {
			assertEquals(1L, eventHashtag.getEventId().longValue());
		}
	
	}
	
	/**
	 * EventHashtag List Hashtag ID로 조회
	 */
	@Test
	public void testSelectEventHashtagListByHashtagId() {
		List<EventHashtagVO> eventHashtagList = mapper.selectEventHashtagListByHashtagId(1L);
		
		log.info(eventHashtagList);
		
		for (EventHashtagVO eventHashtag : eventHashtagList) {
			assertEquals(1L, eventHashtag.getHashtagId().longValue());
		}
	}
	
	/**
	 * EventHashtag Event ID와 Hashtag ID로 조회
	 */
	@Test
	public void testSelectEventHashtagByEventIdAndHashtagId() {
		EventHashtagVO eventHashtag = mapper.selectEventHashtagByEventIdAndHashtagId(1L, 1L);
		
		log.info(eventHashtag);
		
		assertEquals(1L, eventHashtag.getEventId().longValue());
		assertEquals(1L, eventHashtag.getHashtagId().longValue());
		
	}
	
	/**
	 * EventHashtag 생성
	 */
	@Test
	public void testInsertEventHashtag() {
		EventHashtagVO eventHashtag = new EventHashtagVO();
		eventHashtag.setEventId(2L);
		eventHashtag.setHashtagId(1L);
		
		int result = mapper.insertEventHashtag(eventHashtag);
		
		assertEquals(1, result);
	}
	

	/**
	 * EventHashtag 삭제
	 */
	@Test
	public void testDeleteEventHashtag() {
		int result = mapper.deleteEventHashtag(2L, 1L);
		
		log.info(result);
		
		assertEquals(1, result);
	}
	
}
