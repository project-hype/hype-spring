package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.BannerVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.GetMainEventListResponse.GetMainEventResponse;

import lombok.Data;

/**
 * 메인페이지 배너 행사 리스트 DTO
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
public class GetBannerMainEventListResponse {
	List<GetBannerMainEventResponse> eventList = new ArrayList<>();

	public GetBannerMainEventListResponse(List<BannerVO> bannerVO) {
		bannerVO.stream().forEach((event) -> {
			eventList.add(new GetBannerMainEventResponse(event));
		});
	}

	@Data
	public static class GetBannerMainEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		String title;
		String imageUrl;
		String startDate;
		String endDate;
		String branchName;
		String eventTypeName;
		int orderPriority;
		
		public GetBannerMainEventResponse(BannerVO banner) {
			this.eventId = banner.getEvent().getEventId();
			this.branchId = banner.getEvent().getBranchId();
			this.eventTypeId = banner.getEvent().getEventTypeId();
			this.title = banner.getEvent().getTitle();
			this.imageUrl = banner.getEvent().getImageUrl();
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			this.startDate = format.format(banner.getEvent().getStartDate());
			this.endDate = format.format(banner.getEvent().getEndDate());
			
			this.branchName = banner.getEvent().getBranch().getBranchName();
			this.eventTypeName = banner.getEvent().getEventType().getEventTypeName();
			this.orderPriority = banner.getOrderPriority();
		}
	}
}
