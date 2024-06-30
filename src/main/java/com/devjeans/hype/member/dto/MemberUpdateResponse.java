package com.devjeans.hype.member.dto;

import com.devjeans.hype.member.domain.MemberVO;

import lombok.Data;

/**
 * 회원 수정 Response DTO
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
public class MemberUpdateResponse extends MemberLoginResponse {

    public MemberUpdateResponse(MemberVO member) {
    	super(member);
    }
}
