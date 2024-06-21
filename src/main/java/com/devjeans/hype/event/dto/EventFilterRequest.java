package com.devjeans.hype.event.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 이벤트 검색 결과와 필터 조회를 위한 DTO
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
public class EventFilterRequest {

	private String keyword;
	private Date date;
	private Long branchId;
	private List<Long> eventTypeIdList;
}
