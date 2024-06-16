package com.devjeans.hype.member.domain;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
	private Long member_id;
	private int is_admin;
	private String login_id;
	private String name;
	private String password;
	private String gender;
	private Date birthdate;
	private Date create_date;
	private Long city_id;
	private Long prefer_branch_id;
}