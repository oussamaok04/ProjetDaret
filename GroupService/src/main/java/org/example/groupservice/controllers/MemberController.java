package org.example.groupservice.controllers;

import org.example.groupservice.dto.MemberDTO;
import org.example.groupservice.entities.Member;
import org.example.groupservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/search/{id}")
    public Member getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("/search/group/{groupId}")
    public List<Member> getMembersByGroupId(@PathVariable Long groupId) {
        return memberService.getMembersByGroupId(groupId);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMemberById(@PathVariable Long id) {
        return memberService.deleteMember(id);
    }

    @PutMapping("/update/{id}")
    public Member updateMemberById(@PathVariable Long id, @RequestBody MemberDTO dto) {
        return memberService.updateMember(id, dto);
    }
}
