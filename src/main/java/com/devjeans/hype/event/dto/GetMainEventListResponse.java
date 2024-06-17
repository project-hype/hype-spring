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
 * </pre>
 */

@Data
public class GetMainEventListResponse {
	List<GetMainEventResponse> eventList = new ArrayList<>();

	public GetMainEventListResponse(List<EventVO> eventVO) {
		eventVO.stream().forEach((event) -> {
			eventList.add(new GetMainEventResponse(event));
		});
	}

	@Data
	public static class GetMainEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		String title;
		String imageUrl;
		String startDate;
		String endDate;
		String branchName;
		String eventTypeName;
		int viewCount;
		
		public GetMainEventResponse(EventVO event) {
			this.eventId = event.getEventId();
			this.branchId = event.getBranchId();
			this.eventTypeId = event.getEventTypeId();
			this.title = event.getTitle();
			this.imageUrl = event.getImageUrl();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			this.startDate = format.format(event.getStartDate());
			this.endDate = format.format(event.getEndDate());
			
			this.branchName = event.getBranch().getBranchName();
			this.eventTypeName = event.getEventType().getEventTypeName();
			this.viewCount = event.getViewCount();
		}
	
	}
}
