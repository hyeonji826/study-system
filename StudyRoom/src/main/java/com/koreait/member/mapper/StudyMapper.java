package com.koreait.member.mapper;

import com.koreait.member.dto.StudyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudyMapper {
    void save(StudyDTO studyDTO);
    StudyDTO findById(@Param("id") int id);
    List<StudyDTO> findAll();
    List<StudyDTO> findByCreator(@Param("createdBy") int createdBy); // 내가 만든 스터디
}
