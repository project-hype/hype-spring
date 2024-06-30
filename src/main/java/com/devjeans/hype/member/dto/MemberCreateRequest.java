package com.devjeans.hype.member.dto;

import java.util.Date;

import lombok.Data;

/**
 * 회원 생성 DTO
 * @author 임원정
 * @since 2024.06.18
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.18  	임원정        최초 생성
 * </pre>
 */

@Data
public class MemberCreateRequest {
	private String loginId;
	private String name;
	private String password;
	private String gender;
	private Date birthdate;
	private Long cityId;
	private Long preferBranchId;
}
