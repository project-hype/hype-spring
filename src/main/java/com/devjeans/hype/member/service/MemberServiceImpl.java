package com.devjeans.hype.member.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;
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
	public boolean isValidateLoginId(String loginId) {
		// member가 존재하지 않으면 true, 존재하면 false
		return mapper.selectLoginIdByLoginId(loginId) == null;
	}

	/**
	 * 회원 가입
	 */
	@Override
	@Transactional
	public boolean join(MemberVO member) {
		// password bcrypt encoding
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		
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
	

	@Override
	public String getUserPassword(String loginId) {
		return mapper.selectPasswordByLoginId(loginId);
	}

	@Override
	public MemberVO login(MemberVO member) {
		return mapper.selectMemberByLoginIdAndPassword(member);
	}

	@Override
	public MemberVO getMemberInfo(Long memberId) {
		return mapper.selectMemberById(memberId);
	}
	
	
	
}
