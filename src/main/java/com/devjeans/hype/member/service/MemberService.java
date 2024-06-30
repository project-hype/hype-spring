package com.devjeans.hype.member.service;

import java.util.List;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.dto.MemberUpdateRequest;

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
 * 2024.06.20	임원정        회원정보 수정, 삭제 기능 추가
 * </pre>
 */
public interface MemberService {
	// 회원 CRUD
	boolean isValidateLoginId(String loginId) throws Exception;	// ID 중복 확인
	boolean join(MemberVO member) throws Exception;	// 회원가입
	MemberVO login(MemberVO member) throws Exception;	// 로그인
	String getUserPassword(String loginId) throws Exception;	//
	MemberVO getMemberInfo(Long memberId) throws Exception;
	boolean updateMemberInfo(MemberUpdateRequest request) throws Exception;
	boolean deleteMember(Long memberId) throws Exception;
	List<EventVO> getMyFavoriteEvents(Long memberId) throws Exception;
}
