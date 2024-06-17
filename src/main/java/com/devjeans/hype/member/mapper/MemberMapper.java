package com.devjeans.hype.member.mapper;

import java.util.List;

import com.devjeans.hype.member.domain.MemberVO;

public interface MemberMapper {
	public List<MemberVO> getMemberList();
	
	public int insertMember(MemberVO member);	// 회원가입
	public MemberVO getMemberByLoginId(String loginId);	// loginId로 member 가져오기
	public int deleteMember(Long memberId);		// 회원탈퇴
	public int updateMember(MemberVO member); 	// 회원정보 수정
}
