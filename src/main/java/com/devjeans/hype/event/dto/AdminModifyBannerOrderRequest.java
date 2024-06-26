package com.devjeans.hype.event.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.devjeans.hype.event.domain.BannerVO;

import lombok.Data;

/**
 * 배너 정렬 전체 수정 Request DTO (어드민)
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
public class AdminModifyBannerOrderRequest {
	
	@NotNull
	private List<BannerVO> bannerList;

}
