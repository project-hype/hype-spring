package com.devjeans.hype.member.service;

import com.devjeans.hype.member.domain.MemberVO;

public interface MemberService {
	public boolean validateId(String loginId);	// ID 중복 검사
	public boolean join(MemberVO member);
}
