package com.koreait.member.service;

import com.koreait.member.dto.ApplyDTO;
import com.koreait.member.mapper.ApplyMapper;
import com.koreait.member.mapper.StudyMapper;
import com.koreait.member.dto.StudyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;
    @Autowired
    private StudyMapper studyMapper;

    @Override
    public boolean apply(int studyId, int memberId) {
        // 1. 중복 신청 체크
        if (isAlreadyApplied(studyId, memberId)) {
            return false;
        }
        // 2. 정원 초과 체크
        StudyDTO study = studyMapper.findById(studyId);
        int max = study.getMaxMembers();
        int now = countByStudyId(studyId);
        if (now >= max) {
            return false;
        }
        // 3. 신청 저장
        ApplyDTO applyDTO = new ApplyDTO();
        applyDTO.setStudyId(studyId);
        applyDTO.setMemberId(memberId);
        applyDTO.setApplyAt(LocalDateTime.now());
        applyMapper.save(applyDTO);
        return true;
    }

    @Override
    public List<ApplyDTO> findByMemberId(int memberId) {
        return applyMapper.findByMemberId(memberId);
    }

    @Override
    public List<ApplyDTO> findByStudyId(int studyId) {
        return applyMapper.findByStudyId(studyId);
    }

    @Override
    public boolean isAlreadyApplied(int studyId, int memberId) {
        return applyMapper.findByStudyAndMember(studyId, memberId) != null;
    }

    @Override
    public int countByStudyId(int studyId) {
        return applyMapper.countByStudyId(studyId);
    }
}
