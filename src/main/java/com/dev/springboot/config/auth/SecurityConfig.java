package com.dev.springboot.config.auth;

import com.dev.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * EnableWebSecurity : Spring Security 설정들을 활성화
 */
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * csrf().disable().headers().frameOptions().disable()
         *  > h2-console 화면을 사용하기 위해 해당 옵션 disable
         * authorizeRequests()
         *  > URL 별 권한 관리 설정 옵션 시작점
         * antMatchers()
         *  > 권한 관리 대상 지정 옵션
         *  > URL, HTTP 메소드별로 관리 가능
         *  > antMatchers().permitAll() 을 통해 전체 열람 권한을 부여
         *  > antMatchers().hasRole(Role.USER.name()) USER 권한을 가진 사람만 권한 부여
         * anyRequest()
         *  > 설정값 이외 나머지 URL 을 나타냄
         *  > authenticated() 을 통해 인증된 사용자들 (로그인 사용자) 에게만 허용
         * logout()
         *  > 로그아웃 기능 설정 시작점
         * oauth2Login()
         *  > 로그인 기능 설정 시작점
         * userInfoEndpoint()
         *  > Oauth2 로그인 성공 후 사용자 정보를 가져올 때의 설정 담당
         * userService()
         *  > 로그인 성공 시 후속 조치를 진행할 인터페이스 구현체 등록
         *  > 리소스 서버(소셜 서비스) 에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시
         */
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and().authorizeRequests()
                      .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                      .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                      .anyRequest().authenticated()
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
