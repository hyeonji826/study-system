package com.koreait.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApplyDTO {
    private Integer id;
    private Integer studyId;
    private Integer memberId;
    private LocalDateTime applyAt;
}
