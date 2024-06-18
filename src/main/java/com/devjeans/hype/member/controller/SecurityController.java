package com.devjeans.hype.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 스프링 시큐리티 컨트롤러
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
@RequestMapping(value="/",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class SecurityController {
	@GetMapping("/accessError")
	public void accessDenied(Authentication auth, Model model) {
		log.info("access Denied : " + auth);
		model.addAttribute("msg", "Access Denied");
	}
	
	@GetMapping("/loginSuccess")
	public ResponseEntity<String> loginSuccess() {
		return new ResponseEntity<String>("login success", HttpStatus.OK);
	}
	
	@GetMapping("/login")
	public ResponseEntity<String> loginPage(String error, String logout) {
		ResponseEntity<String> result = null;
		log.info("error: "+error);
		log.info("logout: "+logout);
		if(error!=null) {
			result = new ResponseEntity<String>("login failed", HttpStatus.BAD_REQUEST);
			return result;
		}
		
		if(logout!=null) {
			result = new ResponseEntity<String>("logout success", HttpStatus.OK);
			return result; 
		}
		
		return result;
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutGET() {
		log.info("logout");
		return new ResponseEntity<String>("logout success", HttpStatus.OK);
	}
}
