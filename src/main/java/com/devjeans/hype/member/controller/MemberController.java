package com.devjeans.hype.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequestMapping("/member/*")
@AllArgsConstructor
@Controller
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
    public String register(MemberVO member, RedirectAttributes rttr) {
    	log.info("join: "+member);
    	boolean isValidateId = service.validateId(member.getLoginId());
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

}
