package com.example.securitytest.Controller;

import com.example.securitytest.Security.DTO.ClubAuthMemberDTO;
import lombok.extern.java.Log;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.function.Supplier;

@Controller
@Log
@RequestMapping("/sample/")
public class SampleController {

//    로그인하지 않아도 접근 가능
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll...");
    }

//    로그인 한 사람들만 접근 가능
    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal ClubAuthMemberDTO clubAuthMemberDTO) {

        log.info("exMember...");

        log.info((Supplier<String>) clubAuthMemberDTO);

    }

//    관리자 권한이 있는 사용자만 접근 가능
    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin...");
    }

}
