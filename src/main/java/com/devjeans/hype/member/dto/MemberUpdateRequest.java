package com.devjeans.hype.member.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

/**
 * 회원 수정 Request DTO
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
    private String password;
	@NotNull
    private Long cityId;
    private Long preferBranchId;
    private List<Long> category;
    
    
    public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setPassword(this.password);
		member.setCityId(this.cityId);
		member.setPreferBranchId(this.preferBranchId);
		
		return member;
	}

}
