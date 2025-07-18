package com.koreait.member.controller;

import com.koreait.member.dto.ApplyDTO;
import com.koreait.member.dto.MemberDTO;
import com.koreait.member.dto.StudyDTO;
import com.koreait.member.service.ApplyService;
import com.koreait.member.service.MemberService;
import com.koreait.member.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private StudyService studyService;

    // 스터디 신청 (/apply/{studyId} POST 요청)
    @PostMapping("/apply/{studyId}")
    public String apply(@PathVariable int studyId, Authentication authentication, Model model) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);
        boolean result = applyService.apply(studyId, member.getId());

        if (!result) {
            // 실패 시 상세정보를 model에 다시 담기
            StudyDTO study = studyService.findById(studyId);
            MemberDTO m = memberService.findById(study.getCreatedBy());
            java.util.Map<Integer, String> memberNames = new java.util.HashMap<>();
            memberNames.put(study.getCreatedBy(), m != null ? m.getName() : "알수없음");

            model.addAttribute("study", study);
            model.addAttribute("memberNames", memberNames);
            model.addAttribute("msg", "이미 신청했거나 정원이 초과되었습니다.");
            return "Study/studyDetail";
        }
        return "redirect:/myApply";
    }


    // 내가 신청한 스터디 목록
    @GetMapping("/myApply")
    public String myApply(Authentication authentication, Model model) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);

        List<ApplyDTO> applies = applyService.findByMemberId(member.getId());
        List<StudyDTO> myApplyStudies = applies.stream()
                .map(apply -> studyService.findById(apply.getStudyId()))
                .toList();

        // 작성자 이름 map 구성
        java.util.Map<Integer, String> memberNames = new java.util.HashMap<>();
        for (StudyDTO study : myApplyStudies) {
            Integer creatorId = study.getCreatedBy();
            if (!memberNames.containsKey(creatorId)) {
                MemberDTO m = memberService.findById(creatorId);
                memberNames.put(creatorId, m != null ? m.getName() : "알수없음");
            }
        }

        model.addAttribute("myApplyStudies", myApplyStudies);
        model.addAttribute("memberNames", memberNames);
        return "Study/myApply";
    }


    // 내가 만든 스터디 목록
    @GetMapping("/myStudy")
    public String myStudy(Authentication authentication, Model model) {
        String username = authentication.getName();
        MemberDTO member = memberService.findByUsername(username);
        List<StudyDTO> myStudies = studyService.findByCreator(member.getId());
        model.addAttribute("myStudies", myStudies);
        return "Study/myStudy";
    }
}
