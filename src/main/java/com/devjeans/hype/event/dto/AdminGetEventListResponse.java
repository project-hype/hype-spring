package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 행사 정보 리스트 조회 Response DTO (어드민)
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * 2024.06.21  	조영욱        행사 조회에 페이지네이션 출력 후 더 출력할 행사 있는지 반환 추가
 * </pre>
 */

@Data
public class AdminGetEventListResponse {
	List<GetEventResponse> eventList = new ArrayList<>();
	boolean isNextEventExist;

	public AdminGetEventListResponse(List<EventVO> eventVOList) {
		eventVOList.stream().forEach((event) -> {
			eventList.add(new GetEventResponse(event));
		});
	}
	
	public AdminGetEventListResponse(List<EventVO> eventVOList, boolean isNextEventExist) {
		this(eventVOList);
		this.isNextEventExist = isNextEventExist;
	}
	
	
	@Data
	public static class GetEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		Long categoryId;
		String eventTypeName;
		String title;
		String imageUrl;
		String startDate;
		String endDate;
		
		public GetEventResponse(EventVO event) {
			this.eventId = event.getEventId();
			this.branchId = event.getBranchId();
			this.eventTypeId = event.getEventTypeId();
			this.categoryId = event.getCategoryId();
			this.eventTypeName = event.getEventType().getEventTypeName();
			this.title = event.getTitle();
			this.imageUrl = event.getImageUrl();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			this.startDate = format.format(event.getStartDate());
			this.endDate = format.format(event.getEndDate());
		}
	}
}
