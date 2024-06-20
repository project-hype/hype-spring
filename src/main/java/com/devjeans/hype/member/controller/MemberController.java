package com.devjeans.hype.member.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.dto.MemberUpdateRequest;
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
 * 2024.06.18  	임원정        로그인 기능 추가
 * 2024.06.19	임원정        회원가입 관련 기능 수정
 * 2024.06.20	임원정        회원정보 수정 기능 추가
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
	
 	/**
	 * 중복된 아이디 체크
	 * @param request
	 * @return
	 * @throws Exception
	 */	    
	@PostMapping("/checkLoginId")
	public ResponseEntity<String> checkLoginId(@RequestBody MemberVO member) throws Exception {
		log.info("loginId: "+member.getLoginId());
		return service.isValidateLoginId(member.getLoginId())
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 회원 가입
	 * @param request
	 * @return
	 * @throws Exception
	 */	
    @PostMapping("/join")
    public ResponseEntity<String> createMember(@RequestBody MemberVO member) throws Exception {
    	log.info("join: "+member);
    	return service.join(member)
    			? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
    }
    
    /**
	 * 로그인
	 * @param request
	 * @return
	 * @throws Exception
	 */	
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
            loginMember.setPassword(null);
 
            HttpSession session = httpRequest.getSession();
            session.setAttribute("memberId", loginMember.getMemberId());
 
            return ResponseEntity.ok(loginMember);
        } else {
            return ResponseEntity.status(
                       HttpStatus.UNAUTHORIZED).body(loginMember);
        }
    }
    
    /**
	 * 로그인 상태 확인
	 * @param request
	 * @return
	 * @throws Exception
	 */	
    @GetMapping("/checkSession")
    public ResponseEntity<MemberVO> getSessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Long memberId =  (Long) session.getAttribute("memberId");
            if (memberId != null) {
                MemberVO member = service.getMemberInfo(memberId);
                return ResponseEntity.ok(member);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    
    /**
	 * 로그아웃
	 * @param request
	 * @return
	 * @throws Exception
	 */	
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok().build();
    }
    
    /**
	 * 회원 정보 수정
	 * @param request
	 * @return
	 * @throws Exception
	 */	
    
	@PutMapping("/update")
	public ResponseEntity<String> updateMemberInfo(@RequestBody MemberUpdateRequest request) throws Exception {

		return service.updateMemberInfo(request) 
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);

	}
}
