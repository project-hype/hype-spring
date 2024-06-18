package com.devjeans.hype.event.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.HashtagVO;

import lombok.Data;

/**
 * 해시태그 생성 Request DTO (어드민)
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
public class AdminCreateHashtagRequest {
	
	@NotNull
	private String hashtagName;

	public HashtagVO toHashtagVO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, HashtagVO.class);
	}
}
