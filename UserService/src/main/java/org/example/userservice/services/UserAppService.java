package org.example.userservice.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.userservice.dto.UserAppDTO;
import org.example.userservice.entities.UserApp;
import org.example.userservice.repositories.UserAppRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class UserAppService {
    @Autowired
    UserAppRepo userAppRepo;

    //Get all users
    public List<UserApp> getAllUsers(){
        return userAppRepo.findAll();
    }

    //Get user by id
    public UserApp getUserAppById(Long id) throws EntityNotFoundException {
        return userAppRepo.findById(id).orElseThrow(
                () -> new EntityNotFoundException("UserApp with id: " + id + " not found")
        );
    }

    //Get user by email
    public UserApp getUserAppByEmail(String email) throws EntityNotFoundException{
        return userAppRepo.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException("UserApp with email: " + email + " not found")
        );
    }

    //Create user
    public UserApp saveUserApp(UserAppDTO dto){
        return userAppRepo.save(dto.convertToEntity());
    }

    //Delete user
    public String deleteUserApp(Long id){
        userAppRepo.deleteById(id);
        return "UserApp with id: " + id + " deleted";
    }

    //Update User
    public UserApp updateUserApp(UserAppDTO dto, Long id) throws EntityNotFoundException{
        UserApp userApp = getUserAppById(id);

        if(userApp == null){
            throw new EntityNotFoundException("UserApp with id: " + id + " not found");
        }

        userApp.setEmail(dto.email());
        userApp.setFirstName(dto.firstName());
        userApp.setLastName(dto.lastName());
        userApp.setPassword(dto.password());
        userApp.setPhoneNumber(dto.phoneNumber());
        userApp.setUpdatedAt(LocalDateTime.now());

        return userAppRepo.save(userApp);
    }
}
