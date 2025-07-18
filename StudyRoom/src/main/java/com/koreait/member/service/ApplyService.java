package com.koreait.member.service;

import com.koreait.member.dto.ApplyDTO;

import java.util.List;

public interface ApplyService {
    boolean apply(int studyId, int memberId);              // 스터디 신청
    List<ApplyDTO> findByMemberId(int memberId);           // 내가 신청한 스터디
    List<ApplyDTO> findByStudyId(int studyId);             // 해당 스터디의 신청 목록
    boolean isAlreadyApplied(int studyId, int memberId);   // 중복 신청 체크
    int countByStudyId(int studyId);                       // 현재 신청 인원 (정원 체크)
}
