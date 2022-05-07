package com.yejipractice.bookstudy.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 메소드 인자로 세션값을 바로 받을 수 있도록

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치 지정, 메소드의 파라미터로 선언된 객체에서만 사용 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // 이파일을 어노테이션 클래스로 지정
}
