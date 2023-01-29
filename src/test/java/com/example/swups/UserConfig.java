package com.example.swups;

import com.example.swups.entity.User;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class UserConfig {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = TestUtils.getTestUser("Basic");
        basicUser.getAuthority().setName("OTHER");
        User senatUser = TestUtils.getTestUser("Senat");
        senatUser.getAuthority().setName("SENAT");

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser, senatUser
        ));
    }
}