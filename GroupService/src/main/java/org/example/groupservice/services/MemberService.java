package org.example.groupservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.groupservice.dto.MemberDTO;
import org.example.groupservice.dto.mappers.DtoToMember;
import org.example.groupservice.entities.Group;
import org.example.groupservice.entities.Member;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.repositories.GroupRepo;
import org.example.groupservice.repositories.MemberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberRepo memberRepo;
    @Autowired
    private DtoToMember dtoToMember;
    @Autowired
    private GroupService groupService;

    public void joinGroup(Long groupId, Long userId) {

        MemberDTO dto = new MemberDTO(userId, groupId);

        Member member = dtoToMember.convert(dto);
        member.setRole("MEMBER");

        memberRepo.save(member);
    }

    public Member getMemberById(Long id) {
        return memberRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Member with id " + id + " not found")
        );
    }

    public List<Member> getMembersByGroupId(Long groupId) {
        return groupService.getGroupById(groupId).getMembers();
    }

    public String deleteMember(Long id) {
        memberRepo.deleteById(id);
        return "Member with id " + id + " deleted";
    }

    public Member updateMember(Long id, MemberDTO dto) {
        Member member = this.getMemberById(id);

        Member updatedMember = dtoToMember.convert(dto);

        member.setUsername(updatedMember.getUsername());
        member.setUserId(updatedMember.getUserId());
        member.setUserApp(updatedMember.getUserApp());
        member.setGroup(updatedMember.getGroup());

        return memberRepo.save(member);
    }
}
