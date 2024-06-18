package com.devjeans.hype.event.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.CategoryVO;

import lombok.Data;

/**
 * 카테고리 수정 Request DTO (어드민)
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
public class AdminModifyCategoryRequest {
	
	@NotNull
	@Min(1)
	private Long categoryId;

	@NotNull
	private String categoryName;

	public CategoryVO toCategoryVO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, CategoryVO.class);
	}
}
