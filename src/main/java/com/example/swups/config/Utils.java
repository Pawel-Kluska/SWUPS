package com.example.swups.config;

import com.example.swups.entity.Appuser;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class Utils {

    public static Optional<Appuser> getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Appuser) {
            return Optional.of((Appuser) principal);
        }
        return Optional.empty();
    }
}
