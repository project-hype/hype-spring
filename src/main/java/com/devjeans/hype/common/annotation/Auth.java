package com.devjeans.hype.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 로그인 권한 체크 어노테이션
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

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Auth {

	Role role() default Role.MEMBER;

    enum Role {
        MEMBER, // 로그인한 사용자
        ADMIN // 관리자
    }
}
