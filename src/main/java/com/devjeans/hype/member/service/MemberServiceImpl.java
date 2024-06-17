package com.devjeans.hype.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
	public boolean isValidateId(String loginId) {
		MemberVO member = new MemberVO();
		member = mapper.selectMemberByLoginId(loginId);	// 입력된 id와 중복된 id의 멤버가 있는지 조회 
		if(member!=null) {	// member가 존재한다면 false return
			return false;
		}
		return true;
	}

	/**
	 * 회원 가입
	 */
	@Override
	public boolean join(MemberVO member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));	// bcrypt encoding
		int num = mapper.insertMember(member);
		if(num==1) {
			return true;
		}
		return false;
	}
	
	@Override
	public MemberVO getMemberByLoginId(String loginId) {
		return mapper.selectMemberByLoginId(loginId);
	}
	
}
