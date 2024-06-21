package com.devjeans.hype.event.dto;

import lombok.Data;

/**
 * 행사 즐겨찾기 추가 DTO
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
public class CreateFavoriteEventRequest {
	
	private Long eventId;
	private Long memberId;
	

}
