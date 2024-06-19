package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 행사 디테일 DTO (어드민)
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * 2024.06.19  	조영욱        detailAdress 필드 수정
 * </pre>
 */

@Data
public class AdminGetEventDetailResponse {

	Long eventId;
	Long branchId;
	String branchName;
	Long eventTypeId;
	String eventTypeName;
	Long categoryId;
	String categoryName;
	String title;
	String content;
	String imageUrl;
	String startDate;
	String endDate;
	int viewCount;
	String detailAddress;
	
	public AdminGetEventDetailResponse(EventVO event) {
		this.eventId = event.getEventId();
		this.branchId = event.getBranchId();
		this.branchName = event.getBranch().getBranchName();
		this.eventTypeId = event.getEventTypeId();
		this.eventTypeName = event.getEventType().getEventTypeName();
		this.categoryId = event.getCategoryId();
		this.categoryName = event.getCategory().getCategoryName();
		this.title = event.getTitle();
		this.content = event.getContent();
		this.imageUrl = event.getImageUrl();
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.startDate = format.format(event.getStartDate());
		this.endDate = format.format(event.getEndDate());
		this.viewCount = event.getViewCount();
		this.detailAddress = event.getDetailAddress();
	}
}
