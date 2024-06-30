package com.devjeans.hype.member.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.dto.MemberUpdateRequest;

import lombok.extern.log4j.Log4j;

/**
 * 회원 서비스 테스트
 * @author 임원정
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	임원정        최초 생성
 * 2024.06.19	임원정        로그인, 회원가입 테스트 추가
 * 2024.06.20	임원정        회원정보 조회, 수정 테스트 추가
 * 2024.06.21	임원정        즐겨찾기 조회 테스트 추가
 * </pre>
 */

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:**/*-context.xml")
@Log4j
public class MemberServiceTests {
	@Autowired
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * 중복 ID 체크 테스트
	 * @throws Exception
	 */
	@Test
	public void testValidateId() throws Exception {
		String id = "test0618";
		boolean isValidateId = service.isValidateLoginId(id);
		
		assertTrue(isValidateId);
	}
	
	/**
	 * 회원가입 테스트
	 * @throws Exception
	 */
	@Test
	public void testJoin() throws Exception {
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
		
		List<CategoryVO> categoryList = new ArrayList<>();
		
		member.setLoginId("test0618");
		member.setName("테스트");
		member.setPassword("test1234");
		member.setGender("M");
		member.setBirthdate(date);
		member.setCityId(1L);
		member.setPreferBranchId(1L);
		member.setCategory(categoryList);
		
		boolean isJoined = service.join(member);
		assertTrue(isJoined);
	}
	
	/**
	 * 로그인 테스트
	 * @throws Exception 
	 */
	@Test
	public void testLogin() throws Exception {
		MemberVO member = new MemberVO();
		member.setLoginId("test0618");
		member.setPassword("test1234");
		
		MemberVO loginMember = service.login(member);
		assertTrue(loginMember != null);
	}
	
	/**
	 * 회원 정보 조회 테스트
	 * @throws Exception
	 */
	@Test
	public void testGetMemberInfo() throws Exception {
		MemberVO member = service.getMemberInfo(1098L);
		log.info(member);
		assertTrue(member !=null);
	}
	
	/**
	 * 회원 정보 수정 테스트
	 * @throws Exception
	 */
	@Test
	public void testUpdateMemberInfo() throws Exception {
		MemberUpdateRequest member = new MemberUpdateRequest();

		
		List<Long> categoryList = new ArrayList<>();
		

		member.setPassword("1234");
		member.setCityId(2L);
		member.setPreferBranchId(10L);
		member.setCategory(categoryList);
		
		MemberVO result = service.updateMemberInfo(member, 1098L);
		log.info(result);
		assertTrue(result !=null);
	}
	
	/**
	 * 회원 삭제 테스트
	 * @throws Exception
	 */
	@Test
	public void testDeleteMember() throws Exception {
		assertTrue(service.deleteMember(1098L));
	}
	
	/**
	 *  즐겨찾기한 행사 조회
	 * @throws Exception 
	 */
	@Test
	public void testSelectMyFavoriteEvents() throws Exception {
		List<EventVO> list = service.getMyFavoriteEvents(1071L);
		
		log.info(list);
		assertEquals(7, list.size());
	}
}
