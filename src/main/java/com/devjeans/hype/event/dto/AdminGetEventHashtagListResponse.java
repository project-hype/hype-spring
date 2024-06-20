package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.EventHashtagVO;

import lombok.Data;

/**
 * 이벤트-해시태그 Response DTO (어드민)
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
public class AdminGetEventHashtagListResponse {

	List<GetEventHashtagResponse> eventHashtagList = new ArrayList<>();
	
	public AdminGetEventHashtagListResponse(List<EventHashtagVO> eventHashtagVOList) {
		eventHashtagVOList.stream().forEach((eventHashtag) -> {
			eventHashtagList.add(new GetEventHashtagResponse(eventHashtag));
		});
		
	}
	
	@Data
	public static class GetEventHashtagResponse {
		private Long eventId;
		private Long hashtagId;
		private String hashtagName;
		
		public GetEventHashtagResponse(EventHashtagVO eventHashtag) {
			this.eventId = eventHashtag.getEventId();
			this.hashtagId = eventHashtag.getHashtagId();
			this.hashtagName = eventHashtag.getHashtag().getHashtagName();
		}
	}
}
