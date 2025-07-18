package com.koreait.member.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudyDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer maxMembers;
    private LocalDate deadline;
    private Integer createdBy; // member id
}
