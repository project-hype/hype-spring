package com.devjeans.hype.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.BranchVO;
import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventTypeVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;

/**
 * 어드민 행사관리 매퍼 인터페이스
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * 2024.06.19  	조영욱        이벤트 타입, 카테고리, 해시태그 리스트 조회 추가
 * 2024.06.19  	조영욱        이미지 업로드 추가
 * 2024.06.20  	조영욱        배너 CRUD 추가
 * </pre>
 */
public interface AdminEventMapper {


	// Event CRUD
	public EventVO selectEventById(Long eventId);
	public List<EventVO> selectEventListWithPaging(Criteria cri);
	public int insertEvent(EventVO event);
	public int updateEvent(EventVO event);
	public int deleteEvent (Long eventId);
	public List<EventVO> selectEventListEventIdAndTitle();
	
	// Category CRUD
	public CategoryVO selectCategoryById(Long categoryId);
	public List<CategoryVO> selectAllCategory();
	public int insertCategory(CategoryVO category);
	public int updateCategory(CategoryVO category);
	public int deleteCategory(Long categoryId);
	
	// Hashtag CRUD
	public HashtagVO selectHashtagById(Long hashtagId);
	public List<HashtagVO> selectAllHashtag();
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
	
	// EventType
	public List<EventTypeVO> selectAllEventType();
	
	// Branch
	public List<BranchVO> selectAllBranch();
	
	// Banner
	public int insertBanner(BannerVO banner);
	public int deleteBanner(Long eventId);
	public int updateBannerOrderPriority(BannerVO banner);
}
