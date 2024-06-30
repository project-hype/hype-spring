package com.devjeans.hype.member.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.devjeans.hype.event.domain.CategoryVO;
import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

/**
 * 회원 생성 Request DTO
 * @author 임원정
 * @since 2024.06.18
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.18  	임원정        최초 생성
 * 2024.06.30	임원정        코드 리팩토링(Valid 적용, toMemberVO 함수 추가)
 * </pre>
 */

@Data
public class MemberCreateRequest {
	@NotNull
	private String loginId;
	@NotNull
	private String name;
	@NotNull
	@Min(8)
	private String password;
	@NotNull
	private String gender;
	@NotNull
	private Date birthdate;
	@NotNull
	private Long cityId;
	
	private Long preferBranchId;
	private List<CategoryVO> category;
	
	public MemberVO toMemberVO() {
		MemberVO member = new MemberVO();
		member.setLoginId(this.loginId);
		member.setName(this.name);
		member.setPassword(this.password);
		member.setGender(this.gender);
	
    	member.setBirthdate(this.birthdate);
    	
		member.setCityId(this.cityId);
		member.setPreferBranchId(this.preferBranchId);
		member.setCategory(this.category);
		
		return member;
	}
}
