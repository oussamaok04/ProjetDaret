package org.example.groupservice.dto.mappers;

import org.example.groupservice.dto.MemberDTO;
import org.example.groupservice.entities.Group;
import org.example.groupservice.entities.Member;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.feign.clients.UserAppClient;
import org.example.groupservice.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToMember {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserAppClient userAppClient;

    public Member convert(MemberDTO dto) {
        Group group = groupService.getGroupById(dto.groupId());

        UserApp user = userAppClient.getUserById(dto.userId());

        return Member.builder()
                .group(group)
                .userApp(user)
                .userId(dto.userId())
//                .role(user.getRole())
                .username(user.getFirstName() + " " + user.getLastName())
                .build();

    }
}
