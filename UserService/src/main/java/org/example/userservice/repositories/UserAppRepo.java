package org.example.userservice.repositories;

import org.example.userservice.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAppRepo extends JpaRepository<UserApp, Long> {
    Optional<UserApp> findByEmail(String email);
}
