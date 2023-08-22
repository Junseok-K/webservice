package com.webservice.springboot.config.auth;

import com.webservice.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정들을 활성화시켜 줌
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // h2-console 화면을 사용하기 위해 disable
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                
                // URL별 권한 관리 설정 시작(authorizeRequest)
                .authorizeRequests()
                // 권한 관리 대상 지정(antMatchers)
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll() // permitAll: 전체 열람 권한 가능
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // hasRole: 권한을 가진 경우만 가능
                .anyRequest() // 설정된 값들 이외 나머지 URL
                .authenticated() // 인증된(로그인한) 사용자에게만 허용
                .and()

                // 로그아웃 기능에 대한 여러 설정의 시작(logout)
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 이동할 주소
                .and()
                
                .oauth2Login() // OAuth2 로그인 기능에 대한 여러 설정의 진입점
                .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보를 가져올 때의 설정들 담당
                .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService(OAuth2UserService) 인터페이스의 구현체를 등록
    }
}
