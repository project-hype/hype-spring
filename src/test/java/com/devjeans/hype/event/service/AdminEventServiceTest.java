package com.devjeans.hype.event.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;

import lombok.extern.log4j.Log4j;

/**
 * 어드민 행사관리 서비스 테스트
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
public class AdminEventServiceTest {

	@Autowired
	private AdminEventService service;
	
	// *********** Event ************
	
	@Test
	public void testGetEventListWithPaging() {
		Criteria cri = new Criteria();
		try {
			List<EventVO> list = service.getEventListWithPaging(cri);
			list.stream().forEach((event) -> {
				log.info(event);
			});
		} catch (Exception e) {
			log.warn(e);
		}
		
	}
	
	@Test
	public void testCreateEvent() {
		EventVO event = new EventVO();
		event.setBranchId(3L);
		event.setEventTypeId(3L);
		event.setCategoryId(3L);
		event.setTitle("가나다라");
		event.setContent("테스트콘텐츠");
		event.setImageUrl("https://testURL");
		event.setStartDate(new Date());
		event.setEndDate(new Date());
		event.setDetailAddress("3층 해당 매장");
		try {
			boolean result = service.createEvent(event);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testModifyEvent() {
		EventVO event = new EventVO();
		event.setEventId(68L);
		event.setBranchId(4L);
		event.setEventTypeId(3L);
		event.setCategoryId(4L);
		event.setTitle("2가나다라");
		event.setContent("2테스트콘텐츠");
		event.setImageUrl("https://t2estURL");
		event.setStartDate(new Date());
		event.setEndDate(new Date());
		event.setDetailAddress("3층 2해당 매장");
		try {
			boolean result = service.modifyEvent(event);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveEvent() {
		try {
			boolean result = service.removeEvent(67L);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	// *********** Category ************
	@Test
	public void testGetCategoryList() {
		try {
			List<CategoryVO> result = service.getCategoryList();
			result.stream().forEach((r) -> {
				log.info(r);
			});
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testCreateCategory() {
		CategoryVO category = new CategoryVO();
		category.setCategoryName("newCategory3");
		try {
			boolean result = service.createCategory(category);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testModifyCategory() {
		CategoryVO category = new CategoryVO();
		category.setCategoryId(30L);
		category.setCategoryName("수정카테");
		try {
			boolean result = service.modifyCategory(category);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveCategory() {
		try {
			boolean result = service.removeCategory(30L);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	// *********** Hashtag ************
	@Test
	public void testGetHashtagList() {
		try {
			List<HashtagVO> result = service.getHashtagList();
			result.stream().forEach((r) -> {
				log.info(r);
			});
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testCreateHashtag() {
		HashtagVO hashtag = new HashtagVO();
		hashtag.setHashtagName("뉴진스");
		try {
			boolean result = service.createHashtag(hashtag);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testModifyHashtag() {
		HashtagVO hashtag = new HashtagVO();
		hashtag.setHashtagId(10L);
		hashtag.setHashtagName("아일릿");
		
		try {
			boolean result = service.modifyHashtag(hashtag);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveHashtag() {
		try {
			boolean result = service.removeHashtag(11L);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	// *********** EventHashtag ************
	@Test
	public void testGetEventHashtagListByEventId() {
		try {
			List<EventHashtagVO> result = service.getEventHashtagListByEventId(66L);
			result.stream().forEach((r) -> {
				log.info(r);
			});
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testGetEventHashtagListByHashtagId() {
		try {
			List<EventHashtagVO> result = service.getEventHashtagListByHashtagId(1L);
			result.stream().forEach((r) -> {
				log.info(r);
			});
		} catch (Exception e) {
			log.info(e.getMessage());
		}
		
	}
	
	@Test
	public void testCreateEventHashtag() {
		EventHashtagVO eventHashtag = new EventHashtagVO();
		eventHashtag.setEventId(65L);
		eventHashtag.setHashtagId(9L);
		try {
			boolean result = service.createEventHashtag(eventHashtag);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	@Test
	public void testRemoveEventHashtag() {
		try {
			boolean result = service.removeEventHashtag(65L, 9L);
			assertEquals(true, result);
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
	
	
}
