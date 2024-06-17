package com.devjeans.hype.event.domain;

import com.devjeans.hype.member.domain.CityVO;

import lombok.Data;

@Data
public class BranchVO {
	
	private Long branchId;
	private Long cityId;
	private String branchName;
	private String address;
	
	private CityVO city;

}
