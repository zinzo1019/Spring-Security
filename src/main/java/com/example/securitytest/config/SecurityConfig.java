package com.example.securitytest.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * ClubUserDetailsService 작성 이후로 아래 코드는 사용하지 않는다.
     */
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
////        사용자 계정: user1
//        auth.inMemoryAuthentication().withUser("user1")
//
////                1111 패스워드 인코딩 결과
//                .password("$2a$10$GyqhMVMoJGcett.VbIa36e.QvzGSCHdZClW.IdC3GNWIO/hxIVniu")
////                권한 설정: USER
//                .roles("USER");
//
//    }

    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll() // /sample/all에는 login 없이 접근 가능
                .antMatchers("/sample/member").hasRole("USER"); // /sample/member에는 login 후 접근 가능

        http.formLogin(); // 인가/인증 문제시 로그인 화면
        http.csrf().disable(); // csrf 토큰 비활성화 -> 보안을 위헤
        http.logout(); // 검색창에 logout을 치면 로그아웃 되고 다시 login 창이 뜬다.

    }

}
