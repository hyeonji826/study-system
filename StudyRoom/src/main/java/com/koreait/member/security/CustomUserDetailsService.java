package com.koreait.member.security;

import com.koreait.member.dto.MemberDTO;
import com.koreait.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberDTO member = memberMapper.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException("존재하지 않는 아이디입니다.");
        }
        return User.builder()
                .username(member.getUsername())
                .password(member.getPassword()) // 반드시 암호화된 값이어야 함!
                .roles("USER")
                .build();
    }
}
