package org.example.groupservice.dto.mappers;

import org.example.groupservice.dto.GroupDTO;
import org.example.groupservice.entities.Group;
import org.example.groupservice.feign.UserApp;
import org.example.groupservice.feign.clients.UserAppClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DtoToGroup {

    @Autowired
    private UserAppClient userAppClient;

    public Group convert(GroupDTO dto) {
        UserApp user = userAppClient.getUserById(dto.createdById());

        return Group.builder()
                .name(dto.name())
                .description(dto.description())
                .createdAt(LocalDateTime.now())
                .createdByUserId(dto.createdById())
                .createdBy(user)
                .build();
    }
}
