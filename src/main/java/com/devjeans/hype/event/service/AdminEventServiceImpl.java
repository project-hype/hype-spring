package com.devjeans.hype.event.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.devjeans.hype.event.domain.BranchVO;
import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.Criteria;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.EventTypeVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.HashtagVO;
import com.devjeans.hype.event.mapper.AdminEventMapper;
import com.devjeans.hype.util.FileStore;
import com.devjeans.hype.util.UploadFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 어드민 행사관리 서비스 구현체
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * 2024.06.19  	조영욱        이벤트 타입 리스트 조회 추가
 * </pre>
 */

@Service
@RequiredArgsConstructor
@Log4j
public class AdminEventServiceImpl implements AdminEventService {

	private final AdminEventMapper mapper;
	private final FileStore fileStore;
	
	/**
	 * 행사 최근 순 리스트로 조회
	 * 페이지네이션 적용
	 */
	@Override
	public List<EventVO> getEventListWithPaging(Criteria cri) throws Exception {
		if (cri == null) {
			cri = new Criteria();
		}
		return mapper.selectEventListWithPaging(cri);
	}
	
	/**
	 * 행사 아이디로 하나 조회
	 */
	@Override
	public EventVO getEventById(Long eventId) throws Exception {
		return mapper.selectEventById(eventId);
	}
	
	/**
	 * 행사 추가
	 */
	@Override
	@Transactional
	public boolean createEvent(EventVO event, MultipartFile file) throws Exception {
		
		UploadFile uploadFile = fileStore.storeFile(file);
		event.setImageUrl(uploadFile.getStoredFilePath());
		
		return mapper.insertEvent(event) == 1;
	}
	
	/**
	 * 행사 수정
	 */
	public boolean modifyEvent(EventVO event, MultipartFile file) throws Exception {
		
		log.info(file);
		
		if (file != null) {
			UploadFile uploadFile = fileStore.storeFile(file);
			event.setImageUrl(uploadFile.getStoredFilePath());
		}
		
		return mapper.updateEvent(event) == 1;
	}
	
	/**
	 * 행사 삭제
	 */
	public boolean removeEvent(Long eventId) throws Exception {
		return mapper.deleteEvent(eventId) == 1;
	}

	/**
	 * 카테고리 전체 리스트로 조회
	 */
	@Override
	public List<CategoryVO> getCategoryList() throws Exception {
		return mapper.selectAllCategory();
	}
	
	/**
	 * 카테고리 아이디로 하나 조회
	 */
	@Override
	public CategoryVO getCategoryById(Long categoryId) throws Exception {
		return mapper.selectCategoryById(categoryId);
	}

	/**
	 * 카테고리 생성
	 */
	@Override
	public boolean createCategory(CategoryVO category) throws Exception {
		return mapper.insertCategory(category) == 1;
	}

	/**
	 * 카테고리 수정
	 */
	@Override
	public boolean modifyCategory(CategoryVO category) throws Exception {
		return mapper.updateCategory(category) == 1;
	}

	/**
	 * 카테고리 삭제
	 */
	@Override
	public boolean removeCategory(Long categoryId) throws Exception {
		return mapper.deleteCategory(categoryId) == 1;
	}

	/**
	 * 해시태그 전체 리스트 조회
	 */
	@Override
	public List<HashtagVO> getHashtagList() throws Exception {
		return mapper.selectAllHashtag();
	}

	/**
	 * 해시태그 아이디로 하나 조회
	 */
	@Override
	public HashtagVO getHashtagById(Long hashtagId) throws Exception {
		return mapper.selectHashtagById(hashtagId);
	}

	/**
	 * 해시태그 생성
	 */
	@Override
	public boolean createHashtag(HashtagVO hashtag) throws Exception {
		return mapper.insertHashtag(hashtag) == 1;
	}

	/**
	 * 해시태그 수정
	 */
	@Override
	public boolean modifyHashtag(HashtagVO hashtag) throws Exception {
		return mapper.updateHashtag(hashtag) == 1;
	}

	/**
	 * 해시태그 삭제
	 */
	@Override
	public boolean removeHashtag(Long hashtagId) throws Exception {
		return mapper.deleteHashtag(hashtagId) == 1;
	}
	
	/**
	 * 이벤트-해시태그 리스트를 이벤트 아이디로 조회
	 */
	@Override
	public List<EventHashtagVO> getEventHashtagListByEventId(Long eventId) throws Exception {
		return mapper.selectEventHashtagListByEventId(eventId);
	}

	/**
	 * 이벤트-해시태그 리스트를 해시태그 아이디로 조회
	 */
	@Override
	public List<EventHashtagVO> getEventHashtagListByHashtagId(Long hashtagId) throws Exception {
		return mapper.selectEventHashtagListByHashtagId(hashtagId);
	}

	/**
	 * 이벤트-해시태그 추가
	 */
	@Override
	public boolean createEventHashtag(EventHashtagVO eventHashtag) throws Exception {
		return mapper.insertEventHashtag(eventHashtag) == 1;
	}

	/**
	 * 이벤트-해시태그 삭제
	 */
	@Override
	public boolean removeEventHashtag(Long eventId, Long hashtagId) throws Exception {
		return mapper.deleteEventHashtag(eventId, hashtagId) == 1;
	}
	
	/**
	 * 행사 타입 리스트 조회
	 */
	@Override
	public List<EventTypeVO> getEventTypeList() throws Exception {
		return mapper.selectAllEventType();
	}
	
	/**
	 * 브랜치 리스트 조회
	 */
	@Override
	public List<BranchVO> getBranchList() throws Exception {
		return mapper.selectAllBranch();
	}
	
}
