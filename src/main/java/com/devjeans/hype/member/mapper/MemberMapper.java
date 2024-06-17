package com.devjeans.hype.member.mapper;

import java.util.List;

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
 * </pre>
 */
public interface MemberMapper {
	
	// Member CRUD
	public MemberVO selectMemberByLoginId(String loginId);	// 회원가입할 때 id 중복 검사
	public MemberVO selectMemberById(Long memberId);	// 마이페이지 정보 조회
	public int insertMember(MemberVO member);	// 회원가입
	public int deleteMember(Long memberId);		// 회원탈퇴
	public int updateMember(MemberVO member); 	// 회원정보 수정
	
	// MemberCategory CRUD
	public List<MemberCategoryVO> selectMemberCategoryListByMemberId(Long memberId);
	public int insertMemberCategory(MemberCategoryVO memberCategory);
	public int deleteMemberCategory(MemberCategoryVO memberCategory);
}
