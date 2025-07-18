package com.koreait.member.service;

import com.koreait.member.dto.MemberDTO;

public interface MemberService {
    void register(MemberDTO memberDTO);           // 회원가입
    MemberDTO findByUsername(String username);    // 아이디로 회원 찾기
    MemberDTO findById(int id);                   // ID로 회원 찾기
}
