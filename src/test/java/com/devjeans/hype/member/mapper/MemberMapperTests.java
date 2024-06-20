package com.devjeans.hype.member.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.dto.MemberUpdateRequest;

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
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/*****************************
	 ****** Member CRUD Test ******
	 *****************************/
	
	/**
	 * Login ID로 조회
	 */
	@Test
	public void testSelectLoginIdByLoginId() {
		MemberVO member = mapper.selectLoginIdByLoginId("wi");
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
	 * 로그인 ID로 비밀번호 가져오기
	 */
	@Test
	public void testSelectPasswordByLoginId() {
		String loginId = "test123";
		String enteredPassword = "1234";
		// 암호화된 패스워드
        String storedPasswordHash = mapper.selectPasswordByLoginId(loginId);
        log.info(storedPasswordHash);
        // 입력된 비밀번호, 암호화된 비밀번호 비교
        boolean passwordMatches = encoder.matches(enteredPassword, storedPasswordHash);
		assertEquals(true, passwordMatches);
	}
	
	/**
	 * 로그인 ID와 비밀번호에 해당하는 멤버 가져오기
	 */
	@Test
	public void testSelectMemberByLoginIdAndPassword() {
		MemberVO loginMember = new MemberVO();
		loginMember.setLoginId("test123");
		loginMember.setPassword("$2a$10$BWhMdKkeYblzH7Oo.F4eoOYVqobWBYBoAt0GTAKreRCqH1DSVmtzm");
		log.info(loginMember);
		
		MemberVO member = mapper.selectMemberByLoginIdAndPassword(loginMember);
		assertTrue(member!=null);
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

		member.setLoginId("test5");
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
		MemberUpdateRequest request = new MemberUpdateRequest();
		
		request.setMemberId(1030L);
		request.setPassword("1234");
		request.setCityId(16L);
		request.setPreferBranchId(9L);
		
		int result = mapper.updateMember(request);
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
		memberCategory.setMemberId(1030L);
		memberCategory.setCategoryId(2L);
		
		int result = mapper.insertMemberCategory(memberCategory);
		
		assertEquals(1, result);
	}
	
	/**
	 * MemberCategory 삭제
	 */
	@Test
	public void testDeleteMemberCategory() {
		int result = mapper.deleteMemberCategories(1030L);
		
		assertTrue(result>0);
	}
}
