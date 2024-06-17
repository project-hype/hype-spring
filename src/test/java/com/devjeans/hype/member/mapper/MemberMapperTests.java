package com.devjeans.hype.member.mapper;

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
public class MemberMapperTests {
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void testGetMemberList() {
		mapper.getMemberList().forEach(member->log.info(member));
	}
	
	@Test
	public void testGetMember() {
		MemberVO member = mapper.getMemberByLoginId("wi");
		log.info(member);
	}
	
	@Test
	public void testInsertMember() {
		MemberVO member = new MemberVO();
		String bdate = "1998-06-24";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = sdf.parse(bdate);
			log.info(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		member.setLoginId("won4538");
		member.setName("임원정");
		member.setPassword("1234");
		member.setGender("W");
		member.setBirthdate(date);
		member.setCityId(1L);
		member.setPreferBranchId(1L);
		
		int num = mapper.insertMember(member);
		log.info(member);
		log.info(num);
	}
	
	@Test
	public void testDeleteMember() {
		log.info("DELETE COUNT: "+mapper.deleteMember(1002L));
	}
	
	@Test
	public void testUpdateMember() {
		MemberVO member = new MemberVO();
		
		member.setPassword("1234");
		member.setCityId(1L);
		member.setPreferBranchId(1L);
		
		int num = mapper.insertMember(member);
		log.info(member);
		log.info(num);
	}
	
}
