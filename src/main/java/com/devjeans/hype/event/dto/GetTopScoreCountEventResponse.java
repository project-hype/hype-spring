package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.GetEventListResponse.GetEventResponse;

import lombok.Data;

@Data
public class GetTopScoreCountEventResponse {
	List<GetEventResponse> eventList = new ArrayList<>();
	
	public GetTopScoreCountEventResponse(List<EventVO> eventVO) {
		
		eventVO.stream().forEach((event) -> {
			eventList.add(new GetEventResponse(event));
		});
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
		
		public GetEventResponse(EventVO event) {
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
		}
	
	}
}
