package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 메인페이지 행사 리스트 DTO
 * @author 정은지 
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	정은지        최초 생성
 * 2024.06.20   정은지        category_name 컬럼 추가
 * </pre>
 */

@Data
public class GetEventListResponse {
	List<GetEventResponse> eventList = new ArrayList<>();
	boolean isNextEventExist;

	public GetEventListResponse(List<EventVO> eventVO, List<Long> favoriteEventIds) {
		
		eventVO.stream().forEach((event) -> {
			boolean isFavorite = favoriteEventIds.contains(event.getEventId());
			eventList.add(new GetEventResponse(event, isFavorite));
		});
	}
	
	public GetEventListResponse(List<EventVO> eventVO, List<Long> favoriteEventIds, boolean isNextEventExist) {
		this(eventVO, favoriteEventIds);
		this.isNextEventExist = isNextEventExist;
	}

	@Data
	public static class GetEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		String title;
		String imageUrl;
		String startDate;
		String endDate;
		String branchName;
		String categoryName;
		String eventTypeName;
		int viewCount;
		boolean isFavorite;
		
		public GetEventResponse(EventVO event, boolean isFavorite) {
			this.eventId = event.getEventId();
			this.branchId = event.getBranchId();
			this.eventTypeId = event.getEventTypeId();
			this.title = event.getTitle();
			this.imageUrl = event.getImageUrl();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			this.startDate = format.format(event.getStartDate());
			this.endDate = format.format(event.getEndDate());
			
			this.branchName = event.getBranch().getBranchName();
			this.categoryName = event.getCategory().getCategoryName();
			this.eventTypeName = event.getEventType().getEventTypeName();
			this.viewCount = event.getViewCount();
			this.isFavorite = isFavorite;
		}
	}
}
