package com.devjeans.hype.event.dto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.domain.EventHashtagVO;
import com.devjeans.hype.event.domain.HashtagVO;

import lombok.Data;

/**
 * 메인페이지 행사 상세 조회 DTO
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.19  	정은지        최초 생성
 */

@Data
public class GetEventDetailResponse {
	List<GetEventResponse> eventList = new ArrayList<>();

	public GetEventDetailResponse(List<EventVO> eventVOs,
								  List<EventHashtagVO> eventHashtagList,
								  List<Double> scoreList,
								  int favoriteCount,
								  boolean isFavorite) {
		for (EventVO event : eventVOs) {
			List<HashtagVO> hashtags = eventHashtagList.stream()
					.map(EventHashtagVO::getHashtag)
					.collect(Collectors.toList());

			eventList.add(new GetEventResponse(event, hashtags, scoreList, favoriteCount, isFavorite));
		}
	}

	@Data
	public static class GetEventResponse {
		Long eventId;
		Long branchId;
		Long eventTypeId;
		String title;
		String content;
		String imageUrl;
		String startDate;
		String endDate;
		String cityName;
		String branchName;
		String address;
		String detail_address;
		String categoryName;
		String eventTypeName;
		int viewCount;
		int favoriteCount;
		boolean isFavorite;
		List<String> hashtags;
		List<Double> scores;
		Double averageScore;

		public GetEventResponse(EventVO event,
								List<HashtagVO> hashtagList,
								List<Double> scoreList,
								int favoriteCount,
								boolean isFavorite) {

			this.eventId = event.getEventId();
			this.branchId = event.getBranchId();
			this.eventTypeId = event.getEventTypeId();
			this.title = event.getTitle();
			this.content = event.getContent();
			this.imageUrl = event.getImageUrl();

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			this.startDate = format.format(event.getStartDate());
			this.endDate = format.format(event.getEndDate());

			this.cityName = event.getBranch().getCity().getCityName();
			this.branchName = event.getBranch().getBranchName();
			this.address = event.getBranch().getAddress();
			
			this.detail_address = event.getDetailAddress();
			this.categoryName = event.getCategory().getCategoryName();
			this.eventTypeName = event.getEventType().getEventTypeName();
			this.viewCount = event.getViewCount();
			this.favoriteCount = favoriteCount;
			this.isFavorite = isFavorite;

			this.hashtags = hashtagList.stream()
									   .map(HashtagVO::getHashtagName)
									   .collect(Collectors.toList());

			this.scores = new ArrayList<>(scoreList);
			
	        // 점수 평균 계산
	        if (scoreList == null || scoreList.isEmpty()) {
	            this.averageScore = null;
	        } else {
	            this.averageScore = scoreList.stream()
	                                         .filter(score -> score != null)  // null 값 필터링
	                                         .mapToDouble(Double::doubleValue)
	                                         .average()
	                                         .orElse(0.0);
	        }
		}
	
	}
	
}
