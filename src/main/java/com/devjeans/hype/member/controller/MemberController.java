package com.devjeans.hype.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping(value="/member",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class MemberController {
	private BCryptPasswordEncoder passwordEncoder;
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
	
	@GetMapping("/checkLoginId")
	public ResponseEntity<String> checkLoginId(@RequestBody MemberVO member) throws Exception {
		log.info("loginId: "+member.getLoginId());
		return service.isValidateLoginId(member.getLoginId())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/join")
	public void register() {
	}
	
    @PostMapping("/join")
    public ResponseEntity<String> createMember(@RequestBody MemberVO member) throws Exception {
    	log.info("join: "+member);
    	return service.join(member)
    			? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
    }
    
    @PostMapping("/login")
    public ResponseEntity<MemberVO> login(
    @RequestBody Map<String, String> request, HttpServletRequest httpRequest
    ) {
        String loginId = request.get("username");
        String enteredPassword = request.get("password");
 
        // 암호화된 패스워드
        String storedPasswordHash = service.getUserPassword(loginId);
 
        // 입력된 비밀번호, 암호화된 비밀번호 비교
        boolean passwordMatches = passwordEncoder.matches(enteredPassword, storedPasswordHash);
        
        MemberVO member = new MemberVO();
        member.setLoginId(loginId);
        member.setPassword(storedPasswordHash);
        
        MemberVO loginMember = new MemberVO();
        if (passwordMatches) {
            loginMember = service.login(member);
 
            HttpSession session = httpRequest.getSession();
            session.setAttribute("username", loginId);
 
            return ResponseEntity.ok(loginMember);
        } else {
            return ResponseEntity.status(
                       HttpStatus.BAD_REQUEST).body(loginMember);
        }
    }
    
    @GetMapping("/mypage/{memberId}")
    public MemberVO getMember(@PathVariable("memberId") Long memberId) {
    	MemberVO member = service.getMemberInfo(memberId);
    	log.info(member);
    	return member;
    }

}
