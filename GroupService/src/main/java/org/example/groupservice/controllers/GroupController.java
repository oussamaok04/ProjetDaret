package org.example.groupservice.controllers;

import org.example.groupservice.dto.GroupDTO;
import org.example.groupservice.entities.Group;
import org.example.groupservice.services.GroupService;
import org.example.groupservice.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private MemberService memberService;

    @GetMapping("/search/all")
    public List<Group> getGroups() {
        return groupService.getAllGroups();
    }

    @GetMapping("/search/{id}")
    public Group getGroupById(@PathVariable Long id) {
        return groupService.getGroupById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteGroupById(@PathVariable Long id) {
        return groupService.deleteGroupById(id);
    }

    @PostMapping("/add")
    public Group addGroup(@RequestBody GroupDTO dto) {
        return groupService.saveGroup(dto);
    }

    @PutMapping("/update/{id}")
    public Group updateGroup(@PathVariable Long id, @RequestBody GroupDTO dto) {
        return groupService.updateGroupById(id, dto);
    }

    @PostMapping("/join")
    public String joinGroup(@RequestParam Long groupId, @RequestParam Long userId) {
        memberService.joinGroup(groupId, userId);
        return "Member added";
    }
}
