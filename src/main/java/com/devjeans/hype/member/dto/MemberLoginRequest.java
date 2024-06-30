package com.devjeans.hype.member.dto;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

/**
 * 로그인 Request DTO
 * @author 임원정
 * @since 2024.06.21
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.21  	임원정        최초 생성
 * </pre>
 */

@Data
public class MemberLoginRequest {
	private String loginId;
    private String password;
    
    public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setLoginId(this.loginId);
		member.setPassword(this.password);
		
		return member;
	}
}
