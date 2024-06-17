package com.devjeans.hype.event.service;

import java.util.List;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;

/**
 * 어드민 행사관리 서비스 인터페이스
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

public interface AdminEventService {

	// 행사 CRUD
	List<EventVO> getEventListWithPaging(Criteria cri) throws Exception;
	EventVO getEventById(Long eventId) throws Exception;
	boolean createEvent(EventVO event) throws Exception;
	boolean modifyEvent(EventVO event) throws Exception;
	boolean removeEvent(Long eventId) throws Exception;
	
	// 카테고리 CRUD
	List<CategoryVO> getCategoryList() throws Exception;
	CategoryVO getCategoryById(Long categoryId) throws Exception;
	boolean createCategory(CategoryVO category) throws Exception;
	boolean modifyCategory(CategoryVO category) throws Exception;
	boolean removeCategory(Long categoryId) throws Exception;
	
	// 해시태그 CRUD
	List<HashtagVO> getHashtagList() throws Exception;
	HashtagVO getHashtagById(Long hashtagId) throws Exception;
	boolean createHashtag(HashtagVO hashtag) throws Exception;
	boolean modifyHashtag(HashtagVO hashtag) throws Exception;
	boolean removeHashtag(Long hashtagId) throws Exception;
	
	// 이벤트-해시태그 CRUD
	List<EventHashtagVO> getEventHashtagListByEventId(Long eventId) throws Exception;
	List<EventHashtagVO> getEventHashtagListByHashtagId(Long hashtagId) throws Exception;
	boolean createEventHashtag(EventHashtagVO eventHashtag) throws Exception;
	boolean removeEventHashtag(Long eventId, Long hashtagId) throws Exception;

}
