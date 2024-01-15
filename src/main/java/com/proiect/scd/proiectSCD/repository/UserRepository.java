package com.proiect.scd.proiectSCD.repository;

import com.proiect.scd.proiectSCD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
