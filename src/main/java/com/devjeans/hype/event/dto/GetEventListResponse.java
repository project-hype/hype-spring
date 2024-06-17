package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 행사 요약정보 리스트 DTO
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

@Data
public class GetEventListResponse {
	List<GetEventResponse> eventList = new ArrayList<>();

	public GetEventListResponse(List<EventVO> eventVOList) {
		eventVOList.stream().forEach((event) -> {
			eventList.add(new GetEventResponse(event));
		});
		
	}
	
	@Data
	public static class GetEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		Long categoryId;
		String title;
		String imageUrl;
		String startDate;
		String endDate;
		
		public GetEventResponse(EventVO event) {
			this.eventId = event.getEventId();
			this.branchId = event.getBranchId();
			this.eventTypeId = event.getEventTypeId();
			this.categoryId = event.getCategoryId();
			this.title = event.getTitle();
			this.imageUrl = event.getImageUrl();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.startDate = format.format(event.getStartDate());
			this.endDate = format.format(event.getEndDate());
		}
	}
}