package com.devjeans.hype.common.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.devjeans.hype.common.annotation.LoginId;

/**
 * 현재 로그인 아이디 반환 리졸버
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
public class LoginIdArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 파라미터가 @LoginId 어노테이션으로 마크되어 있는지 확인
        return parameter.getParameterAnnotation(LoginId.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session != null) {

        	// 현재 세션에서 memberId를 추출
            return (Long) session.getAttribute("memberId");
        }
        return null; // 세션이 null인 경우 null 반환
    }
}