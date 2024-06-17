package com.devjeans.hype.member.mapper;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;

import lombok.extern.log4j.Log4j;

/**
 * 회원 테스트 클래스
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

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/*-context.xml")
@Log4j
public class MemberMapperTests {
	@Autowired
	private MemberMapper mapper;
	
	/*****************************
	 ****** Member CRUD Test ******
	 *****************************/
	
	/**
	 * Login ID로 조회
	 */
	@Test
	public void testSelectMemberByLoginId() {
		MemberVO member = mapper.selectMemberByLoginId("wi");
		log.info(member);
	}
	
	/**
	 * Member ID로 조회
	 */
	@Test
	public void testSelectMemberById() {
		MemberVO member = mapper.selectMemberById(2L);
		log.info(member);
		assertEquals(2L, member.getMemberId().longValue());
	}
	
	/**
	 * Member 생성
	 */
	@Test
	public void testInsertMember() {
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

		member.setLoginId("test1");
		member.setName("테스트");
		member.setPassword("test1234");
		member.setGender("M");
		member.setBirthdate(date);
		member.setCityId(1L);
		member.setPreferBranchId(7L);
		
		int result = mapper.insertMember(member);
		assertEquals(1, result);
	}
	
	/**
	 * Member 수정
	 */
	@Test
	public void testUpdateMember() {
		MemberVO member = new MemberVO();
		
		member.setMemberId(2L);
		member.setPassword("1234");
		member.setCityId(1L);
		member.setPreferBranchId(1L);
		
		int result = mapper.updateMember(member);
		assertEquals(1, result);
	}
	
	/**
	 * Member 삭제
	 */
	@Test
	public void testDeleteMember() {
		int result = mapper.deleteMember(1002L);
		assertEquals(1, result);
	}
	
	
	/******************************
	 ** MemberCategory CRUD Test **
	 *****************************/
	
	/**
	 * MemberCategory List Member ID로 조회
	 */
	@Test
	public void testSelectMemberCategoryListByMemberId() {
		List<MemberCategoryVO> memberCategoryList = mapper.selectMemberCategoryListByMemberId(9L);
	
		log.info(memberCategoryList);
		
		for (MemberCategoryVO memberCategory : memberCategoryList) {
			assertEquals(9L, memberCategory.getMemberId().longValue());
		}
	}
	
	/**
	 * MemberCategory 생성
	 */
	@Test
	public void testInsertMemberCategory() {
		MemberCategoryVO memberCategory = new MemberCategoryVO();
		memberCategory.setMemberId(9L);
		memberCategory.setCategoryId(1L);
		
		int result = mapper.insertMemberCategory(memberCategory);
		
		assertEquals(1, result);
	}
	
	/**
	 * MemberCategory 삭제
	 */
	@Test
	public void testDeleteMemberCategory() {
		MemberCategoryVO memberCategory = new MemberCategoryVO();
		memberCategory.setMemberId(9L);
		memberCategory.setCategoryId(1L);
		int result = mapper.deleteMemberCategory(memberCategory);
		
		assertEquals(1, result);
	}
}
