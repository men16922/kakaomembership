package com.bm.membership.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * packageName    : com.bm.membership.aop
 * fileName       : HttpLogging
 * author         : men16
 * date           : 2022-06-22
 * description    : HttpLogging 커스텀 어노테이션
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-06-22        men16       최초 생성
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpLogging {
}
