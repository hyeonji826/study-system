package com.koreait.member.controller;

import com.koreait.member.dto.MemberDTO;
import com.koreait.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemberController {

    @Autowired
    private MemberService memberService;

    // 회원가입 폼
    @GetMapping("/register")
    public String registerForm() {
        return "User/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(MemberDTO memberDTO) {
        memberService.register(memberDTO);
        return "redirect:/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String login() {
        return "User/login";
    }

    // 홈(로그인 후 진입)
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);
        model.addAttribute("user", member);
        return "User/home";
    }

    // 마이페이지(정보 수정 화면)
    @GetMapping("/update")
    public String updateForm(Authentication authentication, Model model) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);
        model.addAttribute("user", member);
        return "User/update";
    }

    // 마이페이지 정보 수정(비밀번호 변경 등은 요구조건 아님. 필요시 추가)
    @PostMapping("/update")
    public String update(MemberDTO memberDTO) {
        return "redirect:/home";
    }
}
