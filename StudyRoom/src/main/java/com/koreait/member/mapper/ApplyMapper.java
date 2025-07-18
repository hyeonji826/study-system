package com.koreait.member.mapper;

import com.koreait.member.dto.ApplyDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApplyMapper {
    void save(ApplyDTO applyDTO);
    List<ApplyDTO> findByMemberId(@Param("memberId") int memberId);
    List<ApplyDTO> findByStudyId(@Param("studyId") int studyId);
    ApplyDTO findByStudyAndMember(@Param("studyId") int studyId, @Param("memberId") int memberId);
    int countByStudyId(@Param("studyId") int studyId); // 정원 체크
}
