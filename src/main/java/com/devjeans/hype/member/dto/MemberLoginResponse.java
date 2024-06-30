package com.devjeans.hype.member.dto;

import java.text.SimpleDateFormat;
import java.util.List;

import com.devjeans.hype.member.domain.MemberCategoryVO;
import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

/**
 * 로그인 Response DTO
 * @author 임원정
 * @since 2024.06.30
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.30  	임원정        최초 생성
 * </pre>
 */

@Data
public class MemberLoginResponse {
	private Long memberId;
    private int isAdmin;
    private String loginId;
    private String name;
    private String gender;
    private String birthdate;
    private Long cityId;
    private Long preferBranchId;
    private List<MemberCategoryVO> memberCategory;
    
    public MemberLoginResponse (MemberVO member) {
    	this.memberId = member.getMemberId();
    	this.isAdmin = member.getIsAdmin();
    	this.loginId = member.getLoginId();
    	this.name = member.getName();
    	this.gender = member.getGender();
    	
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	this.birthdate = format.format(member.getBirthdate());
    	
    	this.cityId = member.getCityId();
    	this.preferBranchId = member.getPreferBranchId();
    	this.memberCategory = member.getMemberCategory();
    }
}
