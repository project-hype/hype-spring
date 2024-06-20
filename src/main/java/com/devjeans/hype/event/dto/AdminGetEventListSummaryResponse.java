package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 행사 요약정보 리스트 조회 Response DTO (어드민)
 * @author 조영욱
 * @since 2024.06.20
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.20  	조영욱        최초 생성
 * </pre>
 */

@Data
public class AdminGetEventListSummaryResponse {
	List<GetEventResponse> eventList = new ArrayList<>();

	public AdminGetEventListSummaryResponse(List<EventVO> eventVOList) {
		eventVOList.stream().forEach((event) -> {
			eventList.add(new GetEventResponse(event));
		});
		
	}
	
	@Data
	public static class GetEventResponse {
		Long eventId;
		String title;
		
		public GetEventResponse(EventVO event) {
			this.eventId = event.getEventId();
			this.title = event.getTitle();
		}
	}
}
