package com.devjeans.hype.member.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * 회원 수정 DTO
 * @author 임원정
 * @since 2024.06.20
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.20  	임원정        최초 생성
 * </pre>
 */
@Data
public class MemberUpdateRequest {
	
	@NotNull
	private Long memberId;
	@NotNull
    private String password;
	@NotNull
    private Long cityId;
	
    private Long preferBranchId;
    private List<Long> category;

}
