package com.devjeans.hype.event.dto;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.BannerVO;

import lombok.Data;

/**
 * 배너 생성 Request DTO (어드민)
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
public class AdminCreateBannerRequest {
	
	@NotNull
	private Long eventId;
	@NotNull
	private Long orderPriority;

	public BannerVO toBannerVO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, BannerVO.class);
	}
}
