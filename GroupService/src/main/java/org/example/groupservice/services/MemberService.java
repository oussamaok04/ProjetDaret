package org.example.groupservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.groupservice.dto.MemberDTO;
import org.example.groupservice.dto.mappers.DtoToMember;
import org.example.groupservice.entities.Group;
import org.example.groupservice.entities.Member;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.feign.clients.UserAppClient;
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
    @Autowired
    private UserAppClient userAppClient;

    public void joinGroup(Long groupId, Long userId) {

        MemberDTO dto = new MemberDTO(userId, groupId);
        UserApp userApp = userAppClient.getUserById(userId);

        Member member = dtoToMember.convert(dto);
        member.setRole("MEMBER");
        member.setUserApp(userApp);

        memberRepo.save(member);
    }

    public Member getMemberById(Long id) {
        Member member = memberRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Member with id " + id + " not found")
        );
        UserApp userApp = userAppClient.getUserById(member.getUserId());
        member.setUserApp(userApp);
        return member;
    }

    public List<Member> getMembersByGroupId(Long groupId) {
        return groupService.getGroupById(groupId).getMembers().stream()
                .peek(
                        x -> {
                            UserApp userApp = userAppClient.getUserById(x.getUserId());
                            x.setUserApp(userApp);
                        }
                ).toList();
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
