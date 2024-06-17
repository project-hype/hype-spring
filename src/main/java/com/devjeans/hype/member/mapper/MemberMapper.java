package com.devjeans.hype.member.mapper;

import java.util.List;

import com.devjeans.hype.member.domain.MemberVO;

public interface MemberMapper {
	public List<MemberVO> getMemberList();
	
	public int insertMember(MemberVO member);
	public MemberVO getMember(String loginId);
	public int deleteMember(Long memberId);
}
