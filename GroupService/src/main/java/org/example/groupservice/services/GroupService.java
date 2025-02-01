package org.example.groupservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.groupservice.dto.GroupDTO;
import org.example.groupservice.dto.mappers.DtoToGroup;
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
public class GroupService {

    @Autowired
    private GroupRepo groupRepo;
    @Autowired
    private DtoToGroup dtoToGroup;
    @Autowired
    private UserAppClient userAppClient;
    @Autowired
    private MemberRepo memberRepo;

    public Group getGroupById(Long id) throws EntityNotFoundException {
        Group g = groupRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Le group id " + id + " n'existe pas ")
        );

        UserApp u = userAppClient.getUserById(g.getCreatedByUserId());
        g.setCreatedBy(u);

        return g;
    }

    public List<Group> getAllGroups() {
        return groupRepo.findAll().stream()
                .peek(
                        x -> {
                            UserApp user = userAppClient.getUserById(x.getCreatedByUserId());
                            x.setCreatedBy(user);

                        }
                ).toList();
    }

    public String deleteGroupById(Long id) {
        groupRepo.deleteById(id);
        return "Group with id " + id +" deleted successfully";
    }

    public Group saveGroup(GroupDTO dto) {
//        return groupRepo.save(dtoToGroup.convert(dto));
        Group group = dtoToGroup.convert(dto);
        groupRepo.save(group);
        Member member = Member.builder()
                .username(group.getCreatedBy().getFirstName() + " " + group.getCreatedBy().getLastName())
                .role("ADMIN")
                .userId(group.getCreatedByUserId())
                .group(group)
                .build();
        memberRepo.save(member);
        return group;
    }

    public Group updateGroupById(Long id, GroupDTO dto) {
        Group group = groupRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Le group id " + id + " n'existe pas ")
        );

        Group dtoGroup = dtoToGroup.convert(dto);

        group.setName(dtoGroup.getName());
        group.setDescription(dtoGroup.getDescription());
        group.setCreatedBy(dtoGroup.getCreatedBy());

        return groupRepo.save(group);
    }
}
