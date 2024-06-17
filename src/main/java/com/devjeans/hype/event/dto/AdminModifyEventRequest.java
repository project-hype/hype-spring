package com.devjeans.hype.event.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.EventVO;

import lombok.Data;

@Data
public class AdminModifyEventRequest {
	
	@NotNull
	private Long eventId;
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
