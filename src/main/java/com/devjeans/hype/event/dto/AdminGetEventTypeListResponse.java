package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventTypeVO;

import lombok.Data;

/**
 * 이벤트 타입 리스트 조회 Response DTO (어드민)
 * @author 조영욱
 * @since 2024.06.19
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.19  	조영욱        최초 생성
 * </pre>
 */

@Data
public class AdminGetEventTypeListResponse {

	List<GetEventTypeResponse> eventTypeList = new ArrayList<>();
	
	public AdminGetEventTypeListResponse(List<EventTypeVO> eventTypeVOList) {
		eventTypeVOList.stream().forEach((eventType) -> {
			eventTypeList.add(new GetEventTypeResponse(eventType));
		});
		
	}
	
	@Data
	public static class GetEventTypeResponse {
		private Long eventTypeId;
		private String eventTypeName;
		
		public GetEventTypeResponse(EventTypeVO eventType) {
			this.eventTypeId = eventType.getEventTypeId();
			this.eventTypeName = eventType.getEventTypeName();
		}
	}
}