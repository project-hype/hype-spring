package com.devjeans.hype.member.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.dto.MemberUpdateRequest;
import com.devjeans.hype.member.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 회원 서비스 구현체
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
 * 2024.06.21	임원정        로그인 부분 수정, 회원탈퇴/즐겨찾기 조회 기능 추가	
 * 2024.06.30	임원정        코드 리팩토링(로그인, 회원 수정)
 * </pre>
 */

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	private MemberMapper mapper;
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * ID 중복확인
	 */
	@Override
	public boolean isValidateLoginId(String loginId) throws Exception {
		// member가 존재하지 않으면 true, 존재하면 false
		return mapper.selectLoginIdByLoginId(loginId) == null;
	}

	/**
	 * 회원 가입
	 */
	@Override
	@Transactional
	public boolean join(MemberVO member) throws Exception {
		// 비밀번호 bcrypt encoding
		if(member!=null) {
			member.setPassword(passwordEncoder.encode(member.getPassword()));
		}
		
		// 회원 정보 insert
		int result = mapper.insertMember(member);
		List<CategoryVO> categoryList = member.getCategory();
		
		// 회원 카테고리 insert
		if(categoryList.size()>0) {
			for(CategoryVO category : categoryList) {
				MemberCategoryVO mc = new MemberCategoryVO();
				mc.setMemberId(member.getMemberId());
				mc.setCategoryId(category.getCategoryId());
				mapper.insertMemberCategory(mc);
			}
		}
		
		return result == 1;
	}
	
	/**
	 * 로그인
	 */
	@Override
	public MemberVO login(MemberVO member) throws Exception {
		
		// DB에 저장된 암호화된 패스워드 가져오기
        String storedPasswordHash = mapper.selectPasswordByLoginId(member.getLoginId());

        // 입력된 비밀번호와 암호화된 비밀번호 비교
        boolean passwordMatches = passwordEncoder.matches(member.getPassword(), storedPasswordHash);
        
        if(passwordMatches) {
        	member.setPassword(storedPasswordHash);
        	return mapper.selectMemberByLoginIdAndPassword(member);
        }
        
        return null;
	}
	
	/**
	 * 회원정보 조회
	 */
	@Override
	public MemberVO getMemberInfo(Long memberId) throws Exception {
		return mapper.selectMemberById(memberId);
	}

	/**
	 * 회원 정보 수정
	 */
	@Transactional
    public MemberVO updateMemberInfo(MemberUpdateRequest request, Long memberId) throws Exception {
		MemberVO member = request.toMemberVO();
		member.setMemberId(memberId);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
		// 회원 정보 수정
        int updateResult = mapper.updateMember(member);
        
        // 회원 카테고리 수정
        int deleteResult = mapper.deleteMemberCategories(memberId); // 기존 관심 카테고리 삭제
        
        for (Long categoryId : request.getCategory()) { 
        	MemberCategoryVO mc = new MemberCategoryVO();
        	mc.setMemberId(memberId);
        	mc.setCategoryId(categoryId);
            int insertResult = mapper.insertMemberCategory(mc); // 새로운 관심 카테고리 추가
        }
        
        // 회원이 수정됐다면 수정된 정보 반환
        return updateResult == 1 ? mapper.selectMemberById(memberId) : null;
    }
	
	/**
	 * 회원 삭제
	 */
	@Transactional
	public boolean deleteMember(Long memberId) throws Exception {
		// 자식 레코드(MEMBER_CATEGORY)를 먼저 삭제
		mapper.deleteMemberCategories(memberId);
        
		// 부모 레코드(MEMBER) 삭제
		return mapper.deleteMember(memberId) == 1;
	}
	
	/**
	 * 즐겨찾기한 행사 조회
	 */
	@Override
	public List<EventVO> getMyFavoriteEvents(Long memberId) throws Exception {
		return mapper.selectMyFavoriteEvents(memberId);
	}
}
