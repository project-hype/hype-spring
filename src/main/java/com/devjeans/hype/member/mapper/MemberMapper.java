package com.devjeans.hype.member.mapper;

import java.util.List;

import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;

/**
 * 회원 매퍼 인터페이스
 * @author 임원정
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	임원정        최초 생성
 * 2024.06.18  	임원정        로그인 관련 매퍼 추가
 * 2024.06.19	임원정        회원 CRUD, 관심 카테고리 CRUD 추가
 * 2024.06.21	임원정        즐겨찾기 조회 추가
 * 2024.06.30	임원정        회원정보 수정 시 수정된 정보 return
 * </pre>
 */

public interface MemberMapper {
	
	// Member CRUD
	public MemberVO selectLoginIdByLoginId(String loginId);	// 회원가입할 때 id 중복 검사
	public MemberVO selectMemberById(Long memberId);	// 마이페이지 정보 조회
	public String selectPasswordByLoginId(String loginId);	// 비밀번호 일치하는지 확인
	public MemberVO selectMemberByLoginIdAndPassword(MemberVO member);	// 로그인
	public int insertMember(MemberVO member);	// 회원가입
	public int deleteMember(Long memberId);		// 회원탈퇴
	public int updateMember(MemberVO member); 	// 회원정보 수정
	
	// MemberCategory CRUD
	public List<MemberCategoryVO> selectMemberCategoryListByMemberId(Long memberId); // 관심 카테고리 가져오기
	public int insertMemberCategory(MemberCategoryVO memberCategory);	// 관심 카테고리 추가
	public int deleteMemberCategories(Long memberId);	// 관심 카테고리 삭제
	
	// Favorite Event
	public List<EventVO> selectMyFavoriteEvents(Long memberId);	// 즐겨찾기한 행사 리스트 조회
}
