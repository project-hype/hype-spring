package com.devjeans.hype.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devjeans.hype.aop.Auth;
import com.devjeans.hype.aop.LoginId;
import com.devjeans.hype.event.domain.EventVO;
import com.devjeans.hype.event.dto.GetEventListResponse;
import com.devjeans.hype.member.dto.MemberCreateRequest;
import com.devjeans.hype.member.dto.MemberLoginRequest;
import com.devjeans.hype.member.dto.MemberLoginResponse;
import com.devjeans.hype.member.dto.MemberUpdateRequest;
import com.devjeans.hype.member.dto.MemberUpdateResponse;
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
 * 2024.06.18  	임원정        회원가입 관련 기능 추가
 * 2024.06.19	임원정        로그인 기능 추가, 회원가입 수정
 * 2024.06.20	임원정        회원정보 조회, 수정 기능 추가
 * 2024.06.21	임원정        회원탈퇴, 즐겨찾기 조회 기능 추가
 * 2024.06.30	임원정        코드 리팩토링(로그인 부분수정, DTO, Auth, LoginId 어노테이션 적용)
 * 2024.06.30	임원정        회원정보 수정 시 수정된 정보 return
 * </pre>
 */

@RestController
@RequestMapping(value="/member",
		produces=MediaType.APPLICATION_JSON_VALUE)
@Log4j
@AllArgsConstructor
public class MemberController {
	private MemberService service;
	
 	/**
 	 * 중복 ID 여부 확인
 	 * @param loginId
 	 * @return
 	 * @throws Exception
 	 */
	@GetMapping("/checkLoginId/{loginId}")
	public ResponseEntity<String> checkLoginId(@PathVariable String loginId) throws Exception {
		log.info("loginId: "+loginId);
		return service.isValidateLoginId(loginId)
				? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 회원가입
	 * @param request
	 * @return
	 * @throws Exception
	 */
    @PostMapping("/join")
    public ResponseEntity<String> createMember(@RequestBody @Valid MemberCreateRequest request) throws Exception {
    	return service.join(request.toMemberVO())
    			? new ResponseEntity<String>("success", HttpStatus.OK)
				: new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
    }
    
    
    /**
     * 로그인
     * @param request
     * @param httpRequest
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> login(@RequestBody MemberLoginRequest request, HttpServletRequest httpRequest) throws Exception {
    	MemberLoginResponse loginMember = new MemberLoginResponse(service.login(request.toMemberVO()));
        if (loginMember != null) {
            HttpSession session = httpRequest.getSession();
            session.setAttribute("memberId", loginMember.getMemberId());
            return new ResponseEntity<MemberLoginResponse>(loginMember, HttpStatus.OK);
        } else {
            return new ResponseEntity<MemberLoginResponse>(loginMember, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * 로그아웃
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        return ResponseEntity.ok().build();
    }
    
    /**
     * 회원정보 수정
     * @param request
     * @param memberId
     * @return
     * @throws Exception
     */
    @Auth()
	@PutMapping("/update")
	public ResponseEntity<MemberUpdateResponse> updateMemberInfo(@RequestBody MemberUpdateRequest request, @LoginId Long memberId) throws Exception {
    	MemberUpdateResponse loginMember = new MemberUpdateResponse(service.updateMemberInfo(request, memberId));
    	return loginMember != null
				? new ResponseEntity<MemberUpdateResponse>(loginMember, HttpStatus.OK)
				: new ResponseEntity<MemberUpdateResponse>(loginMember, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * 회원 탈퇴
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
    @Auth()
	@DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(@LoginId Long memberId) throws Exception {
		try {
			// 회원 삭제 서비스 호출
			boolean deleted = service.deleteMember(memberId);
			if (deleted) {
				// 회원 삭제 성공 시 세션 만료 처리
				return ResponseEntity.ok().header("Session-Expired", "true").body("success");
			} else {
				// 회원 삭제 실패 시 클라이언트에게 에러 응답
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
			}
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", e);
		}
    }
	
	/**
	 * 즐겨찾기 조회
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
    @Auth()
	@GetMapping("/favorites")
    public GetEventListResponse getFavoriteEvents(@LoginId Long memberId) throws Exception {
		List<EventVO> list = service.getMyFavoriteEvents(memberId);
		return new GetEventListResponse(list);
    }
}
