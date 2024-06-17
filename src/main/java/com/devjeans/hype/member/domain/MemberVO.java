package com.devjeans.hype.member.domain;

import java.util.Date;
import java.util.List;

import com.devjeans.hype.event.domain.BranchVO;
import com.devjeans.hype.event.domain.CategoryVO;

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
	
	private CategoryVO category;
	private CityVO city;
	private BranchVO branch;
	private List<FavoriteVO> favoriteList;
}