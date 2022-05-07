package com.yejipractice.bookstudy.config.auth;

import com.yejipractice.bookstudy.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                // h2-console 화면을 사용하기 위해 해당 옵션들은 disabled
                .and()
                .authorizeRequests()
                //  URL별 권환 관리를 설정하는 옵션의 시작점
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**", "/h2-console/**").permitAll()
                // 권한 관리 대상을 지정하는 옵션
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                // "api/v1/** " 주소를 가진 api는 user 권한을 가진사람만 접근 가능하도록
                .anyRequest()// 설정된 값들 이외의 나머지 URL
                .authenticated() // 나머지 URL들은 모두 인증된 사용자(로그인한 사용자)들에게만 허용하는 옵션
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 기능에 대한 설정 진입점, 로그아웃 성공시 / 주소로 이동
                .and()
                .oauth2Login()
                // OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint()
                //  OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정을 담당하는 옵션
                .userService(customOAuth2UserService);
                // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    }
}
