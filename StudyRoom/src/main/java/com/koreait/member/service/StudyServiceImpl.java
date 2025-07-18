package com.koreait.member.service;

import com.koreait.member.dto.StudyDTO;
import com.koreait.member.mapper.StudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {

    @Autowired
    private StudyMapper studyMapper;

    @Override
    public void createStudy(StudyDTO studyDTO) {
        studyMapper.save(studyDTO);
    }

    @Override
    public StudyDTO findById(int id) {
        return studyMapper.findById(id);
    }

    @Override
    public List<StudyDTO> findAll() {
        return studyMapper.findAll();
    }

    @Override
    public List<StudyDTO> findByCreator(int createdBy) {
        return studyMapper.findByCreator(createdBy);
    }
}
