package com.example.swups.repository;

import com.example.swups.entity.Appuser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<Appuser, Integer> {

    Optional<Appuser> findByLogin(String login);
}
