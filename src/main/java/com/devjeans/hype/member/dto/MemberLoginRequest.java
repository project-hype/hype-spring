package com.devjeans.hype.member.dto;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

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
