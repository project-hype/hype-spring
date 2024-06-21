package com.devjeans.hype.event.dto;

import org.modelmapper.ModelMapper;

import com.devjeans.hype.event.domain.StarScoreVO;

import lombok.Data;

/**
 * 행사 별점 추가 DTO
 * @author 정은지 
 * @since 2024.06.20
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.20  	정은지        최초 생성
 * </pre>
 */

@Data
public class CreateStarScoreRequest {
	
	private Long memberId;
	private Long eventId;
	private double score;	
	
	public StarScoreVO toStarScoreVO() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(this, StarScoreVO.class);
	}

}
