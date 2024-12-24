package org.example.userservice.controllers;

import org.example.userservice.dto.UserAppDTO;
import org.example.userservice.entities.UserApp;
import org.example.userservice.services.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserAppService userAppService;

    @GetMapping("/search/all")
    public List<UserApp> getAllUsers() {
        return userAppService.getAllUsers();
    }

    @GetMapping("/search/{id}")
    public UserApp getUserById(@PathVariable Long id) {
        return userAppService.getUserAppById(id);
    }

    @GetMapping("/search/email/{email}")
    public UserApp getUserByEmail(@PathVariable String email) {
        return userAppService.getUserAppByEmail(email);
    }

    @PostMapping("/add")
    public UserApp addUser(@RequestBody UserAppDTO dto) {
        return userAppService.saveUserApp(dto);
    }

    @PutMapping("/update/{id}")
    public UserApp updateUser(@PathVariable Long id, @RequestBody UserAppDTO dto) {
        return userAppService.updateUserApp(dto, id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userAppService.deleteUserApp(id);
    }
}
