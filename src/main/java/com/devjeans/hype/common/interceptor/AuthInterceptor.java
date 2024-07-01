package com.devjeans.hype.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.devjeans.hype.common.annotation.Auth;
import com.devjeans.hype.common.annotation.Auth.Role;
import com.devjeans.hype.member.domain.MemberVO;
import com.devjeans.hype.member.service.MemberService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;

/**
 * 로그인 권한 체크 인터셉터
 * @author 조영욱
 * @since 2024.06.22
 * @version 1.0
 *
 * <pre>
 * 수정일        	수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.06.22  	조영욱        최초 생성
 * </pre>
 */

@Component
@NoArgsConstructor
@Log4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	HandlerMethod handlerMethod = (HandlerMethod) handler;
    	if (!(handler instanceof HandlerMethod)) {
            return true;
        }
          	
    	HttpSession session = request.getSession(false);
    	
    	// 메소드 어노테이션 확인
    	Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
    	
    	// 클래스 수준의 Auth 어노테이션 확인
    	if (auth == null) {            
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

    	// Auth 어노테이션 안 달렸을 경우
    	if (auth == null) {
    		return true;
    	}

        if (session != null) {
            Long memberId =  (Long) session.getAttribute("memberId");

            if (memberId != null) {
            	MemberVO member = memberService.getMemberInfo(memberId);

            	// 멤버 권한
            	if (auth.role().compareTo(Role.MEMBER) == 0) {
            		if (member != null) {
            			return true;
            		}
            	}
            	
            	// 어드민 권한
            	if (auth.role().compareTo(Role.ADMIN) == 0) {
            		if (member.getIsAdmin() == 1) {
            			return true;
            		}
            	}
            }
        }
        
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%");
    	log.info("권한이 부족합니다");
    	log.info("필요 권한: " + auth.role());
    	log.info("%%%%%%%%%%%%%%%%%%%%%%%%");

        throw new Exception();
    }

}