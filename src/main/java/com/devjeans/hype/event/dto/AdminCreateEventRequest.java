package com.devjeans.hype.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

/**
 * 행사 생성 DTO (어드민)
 * @author 조영욱
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	조영욱        최초 생성
 * </pre>
 */

@Data
public class AdminCreateEventRequest {

	@NotNull
	private Long branchId;
	@NotNull
	private Long eventTypeId;
	@NotNull
	private Long categoryId;
	@NotNull
	private String title;
	@NotNull
	private String content;
	@NotNull
	private String imageUrl;
	@NotNull
	private Date startDate;
	@NotNull
	private Date endDate;
	@NotNull
	private String detailAddress;
	
	public EventVO toEventVO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, EventVO.class);
	}
}
