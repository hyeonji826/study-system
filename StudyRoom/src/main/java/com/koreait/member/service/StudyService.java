package com.koreait.member.service;

import com.koreait.member.dto.StudyDTO;

import java.util.List;

public interface StudyService {
    void createStudy(StudyDTO studyDTO);           // 스터디 개설
    StudyDTO findById(int id);                     // ID로 스터디 조회
    List<StudyDTO> findAll();                      // 전체 스터디
    List<StudyDTO> findByCreator(int createdBy);   // 내가 만든 스터디
}
