package com.devjeans.hype.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;

public interface AdminEventMapper {


	// Event CRUD
	public EventVO selectEventById(Long eventId);
	public int insertEvent(EventVO event);
	public int updateEvent(EventVO event);
	public int deleteEvent (Long eventId);
	
	// Category CRUD
	public CategoryVO selectCategoryById(Long categoryId);
	public int insertCategory(CategoryVO category);
	public int updateCategory(CategoryVO category);
	public int deleteCategory(Long categoryId);
	
	// EventType CRUD : 보류
//	public EventTypeVO selectEventTypeById(Long eventTypeId);
//	public int insertEventType(EventTypeVO eventType);
//	public int updateEventType(EventTypeVO eventType);
//	public int deleteEventType(Long eventTypeId);
	
	// Hashtag CRUD
	public HashtagVO selectHashtagById(Long hashtagId);
	public int insertHashtag(HashtagVO hashtag);
	public int updateHashtag(HashtagVO hashtag);
	public int deleteHashtag(Long hashtagId);
	
	// EventHashTag CRUD
	public List<EventHashtagVO> selectEventHashtagListByEventId(Long eventId);
	public List<EventHashtagVO> selectEventHashtagListByHashtagId(Long hashtagId);
	public EventHashtagVO selectEventHashtagByEventIdAndHashtagId(
			@Param("eventId") Long eventId,
			@Param("hashtagId") Long hashtagId);
	public int insertEventHashtag(EventHashtagVO eventHashtag);
//	public int updateEventHashtag(EventHashtagVO eventHashtag); 필요 없음
	public int deleteEventHashtag(
			@Param("eventId") Long eventId,
			@Param("hashtagId") Long hashtagId);
	
}
