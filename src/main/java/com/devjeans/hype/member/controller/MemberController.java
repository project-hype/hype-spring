package com.devjeans.hype.member.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 회원 컨트롤러
 * @author 임원정
 * @since 2024.06.17
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.17  	임원정        최초 생성
 * </pre>
 */

@RestController
@RequestMapping(value="/member/*",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class MemberController {
	private MemberService service;
	
	@GetMapping("/mypage")
	public String doMember() {
		log.info("logined member");
		return "member/mypage";
	}
	
	@GetMapping("/admin")
	public String doAdmin() {
		log.info("admin only");
		return "member/admin";
	}
	
	@GetMapping("/join")
    public void register() {
    }

    @PostMapping("/join")
    public String register(MemberVO member, RedirectAttributes rttr) throws Exception {
    	log.info("join: "+member);
    	boolean isValidateId = service.isValidateId(member.getLoginId());
    	if(isValidateId) {
    		boolean isJoined = service.join(member);
    		if(isJoined) {
    			log.info("회원가입 되었습니다");
    			return "redirect:/login"; // 로그인 페이지로 리다이렉트
    		}
    	}
    	else {
    		log.info("중복된 id입니다");
    	}
    	return "redirect:/member/join";
    }
    
    @GetMapping("/mypage/{loginId}")
    public MemberVO getMember(@PathVariable("loginId") String loginId) {
    	MemberVO member = service.getMemberByLoginId(loginId);
    	log.info(member);
    	return member;
    }

}
