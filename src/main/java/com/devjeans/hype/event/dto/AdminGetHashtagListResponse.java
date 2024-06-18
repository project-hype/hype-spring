package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.HashtagVO;

import lombok.Data;

/**
 * 해시태그 리스트 조회 Response DTO (어드민)
 * @author 조영욱
 * @since 2024.06.18
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.18  	조영욱        최초 생성
 * </pre>
 */

@Data
public class AdminGetHashtagListResponse {

	List<GetHashtagResponse> hashtagList = new ArrayList<>();
	
	public AdminGetHashtagListResponse(List<HashtagVO> hashtagVOList) {
		hashtagVOList.stream().forEach((hashtag) -> {
			hashtagList.add(new GetHashtagResponse(hashtag));
		});
		
	}
	
	@Data
	public static class GetHashtagResponse {
		private Long hashtagId;
		private String hashtagName;
		
		public GetHashtagResponse(HashtagVO hashtag) {
			this.hashtagId = hashtag.getHashtagId();
			this.hashtagName = hashtag.getHashtagName();
		}
	}
}
