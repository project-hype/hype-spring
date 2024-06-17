package com.devjeans.hype.member.domain;

import com.devjeans.hype.event.domain.CategoryVO;

import lombok.Data;

@Data
public class MemberCategoryVO {
	private Long memberId;
	private Long categoryId;
	
	private MemberVO member;
	private CategoryVO category;
}
