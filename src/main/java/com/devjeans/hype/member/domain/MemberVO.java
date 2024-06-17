package com.devjeans.hype.member.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private Long memberId;
	private int isAdmin;
	private String loginId;
	private String name;
	private String password;
	private String gender;
	private Date birthdate;
	private Date createDate;
	private Long cityId;
	private Long preferBranchId;
}