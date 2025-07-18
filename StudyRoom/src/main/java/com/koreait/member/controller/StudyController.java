package com.koreait.member.controller;

import com.koreait.member.dto.StudyDTO;
import com.koreait.member.dto.MemberDTO;
import com.koreait.member.service.StudyService;
import com.koreait.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudyController {

    @Autowired
    private StudyService studyService;
    @Autowired
    private MemberService memberService;

    // 전체 스터디 목록
    @GetMapping("/studyList")
    public String studyList(Model model) {
        List<StudyDTO> studies = studyService.findAll();
        Map<Integer, String> memberNames = new HashMap<>();
        for (StudyDTO s : studies) {
            if (!memberNames.containsKey(s.getCreatedBy())) {
                MemberDTO m = memberService.findById(s.getCreatedBy());
                memberNames.put(s.getCreatedBy(), m != null ? m.getName() : "알수없음");
            }
        }
        model.addAttribute("studies", studies);
        model.addAttribute("memberNames", memberNames);
        return "Study/studyList";
    }

    // 스터디 개설 폼
    @GetMapping("/study/create")
    public String createForm() {
        return "Study/studyRoomCreate";
    }

    // 스터디 개설 처리
    @PostMapping("/study/create")
    public String create(@ModelAttribute StudyDTO studyDTO, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);
        studyDTO.setCreatedBy(member.getId());
        studyService.createStudy(studyDTO);
        return "redirect:/studyList";
    }

    // 스터디 상세 보기
    @GetMapping("/study/{id}")
    public String detail(@PathVariable int id, Model model) {
        StudyDTO study = studyService.findById(id);
        Map<Integer, String> memberNames = new HashMap<>();
        MemberDTO m = memberService.findById(study.getCreatedBy());
        memberNames.put(study.getCreatedBy(), m != null ? m.getName() : "알수없음");
        model.addAttribute("study", study);
        model.addAttribute("memberNames", memberNames);
        return "Study/studyDetail";
    }


}
