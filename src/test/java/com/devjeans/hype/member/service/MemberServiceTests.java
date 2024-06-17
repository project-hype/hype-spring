package com.devjeans.hype.member.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/*-context.xml")
@Log4j
public class MemberServiceTests {
	@Autowired
	private MemberService service;
	
	@Test
	public void testValidateId() {
		String id = "won4538";
		boolean isValidateId = service.validateId(id);
		log.info(isValidateId);
	}
	
	@Test
	public void testJoin() {
		MemberVO member = new MemberVO();
		String bdate = "2000-01-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(bdate);
			log.info(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//member.setIsAdmin(1);
		member.setLoginId("admin2");
		member.setName("관리자");
		member.setPassword("test1234");
		member.setGender("M");
		member.setBirthdate(date);
		member.setCityId(1L);
		member.setPreferBranchId(1L);
		
		boolean isJoined = service.join(member);
		log.info(member);
		log.info(isJoined);
	}
}
