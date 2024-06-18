package com.devjeans.hype.event.dto;

import java.util.ArrayList;
import java.util.List;

import com.devjeans.hype.event.domain.CategoryVO;

import lombok.Data;

/**
 * 카테고리 리스트 조회 Response DTO (어드민)
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
public class AdminGetCategoryListResponse {

	List<GetCategoryResponse> categoryList = new ArrayList<>();
	
	public AdminGetCategoryListResponse(List<CategoryVO> categoryVOList) {
		categoryVOList.stream().forEach((category) -> {
			categoryList.add(new GetCategoryResponse(category));
		});
		
	}
	
	@Data
	public static class GetCategoryResponse {
		private Long categoryId;
		private String categoryName;
		
		public GetCategoryResponse(CategoryVO category) {
			this.categoryId = category.getCategoryId();
			this.categoryName = category.getCategoryName();
		}
	}
}
