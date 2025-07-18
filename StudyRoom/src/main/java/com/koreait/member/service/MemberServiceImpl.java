package com.koreait.member.service;

import com.koreait.member.dto.MemberDTO;
import com.koreait.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void register(MemberDTO memberDTO) {
        // 비밀번호 암호화 후 저장
        memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
        memberMapper.save(memberDTO);
    }

    @Override
    public MemberDTO findByUsername(String username) {
        return memberMapper.findByUsername(username);
    }

    @Override
    public MemberDTO findById(int id) {
        return memberMapper.findById(id);
    }
}
