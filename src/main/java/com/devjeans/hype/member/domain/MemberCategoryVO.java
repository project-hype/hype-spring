package com.devjeans.hype.member.domain;

import java.util.List;

import com.devjeans.hype.event.domain.CategoryVO;

import lombok.Data;

@Data
public class MemberCategoryVO {
	private Long memberId;
	private Long categoryId;
	
	private List<CategoryVO> category;
}
