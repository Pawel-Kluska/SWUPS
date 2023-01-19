package com.example.swups.repository;

import com.example.swups.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}
