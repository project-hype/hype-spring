package com.devjeans.hype.member.service;

import com.devjeans.hype.member.domain.MemberVO;

/**
 * 회원 서비스 인터페이스
 * @author 임원정
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	임원정        최초 생성
 * 2024.06.18  	임원정        로그인 기능 추가
 * </pre>
 */
public interface MemberService {
	// 회원 CRUD
	boolean isValidateLoginId(String loginId) throws Exception;	// ID 중복 확인
	boolean join(MemberVO member);	// 회원가입
	String getUserPassword(String loginId);
	MemberVO login(MemberVO member);
	MemberVO getMemberInfo(Long memberId);
}
