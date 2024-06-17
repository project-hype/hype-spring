package com.devjeans.hype.member.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
	private MemberMapper mapper;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public boolean validateId(String loginId) {
		MemberVO member = new MemberVO();
		member = mapper.getMemberByLoginId(loginId);	// 입력된 id와 중복된 id의 멤버가 있는지 조회 
		
		if(member!=null) {	// member가 존재한다면 false return
			return false;
		}
		
		return true;
	}

	@Override
	public boolean join(MemberVO member) {
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		int num = mapper.insertMember(member);
		if(num==1) {
			return true;
		}
		return false;
	}
	
}
