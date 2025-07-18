package com.koreait.member.mapper;

import com.koreait.member.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDTO findByUsername(@Param("username") String username);
    void save(MemberDTO memberDTO);
    MemberDTO findById(@Param("id") int id);
}
