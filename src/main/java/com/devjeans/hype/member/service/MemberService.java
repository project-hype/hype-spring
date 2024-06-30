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
 * 2024.06.18  	임원정        회원가입 관련 기능 추가
 * 2024.06.19	임원정        로그인 기능 추가, 회원가입 수정
 * 2024.06.20	임원정        회원정보 조회, 수정 기능 추가
 * 2024.06.21	임원정        로그인 부분 수정, 회원탈퇴/줄겨찾기 조회 기능 추가
 * 2024.06.30	임원정        코드 리팩토링(로그인, 회원 수정)
 * </pre>
 */
public interface MemberService {
	// 회원 가입
	boolean isValidateLoginId(String loginId) throws Exception;	// ID 중복 확인
	boolean join(MemberVO member) throws Exception;	// 회원가입
	
	// 로그인
	MemberVO login(MemberVO member) throws Exception;	// 로그인
	
	// 마이페이지
	MemberVO getMemberInfo(Long memberId) throws Exception;	// 회원 조회
	MemberVO updateMemberInfo(MemberUpdateRequest request, Long memberId) throws Exception;	// 회원 수정	
	boolean deleteMember(Long memberId) throws Exception;	// 회원 삭제
	List<EventVO> getMyFavoriteEvents(Long memberId) throws Exception;	// 즐겨찾기 조회
}
