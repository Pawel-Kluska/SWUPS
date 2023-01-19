package com.example.swups;

import com.example.swups.entity.Authority;
import com.example.swups.entity.User;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.List;

public class TestUtils {

    public static User getTestUser(String name) {
        Authority authority = Authority.builder()
                .name("ROLE")
                .build();

        User user = User.builder()
                .name(name)
                .surname("surname")
                .login("login")
                .password("password")
                .authority(authority)
                .build();

        return user;
    }

    public static void logInUser() {
        UserDetails user = User.builder()
                .login("login")
                .password("password").build();

        Authentication auth = new TestingAuthenticationToken(user, null);
        SecurityContext context = new SecurityContextImpl();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);
    }

    public static void logOutUser() {
        AnonymousAuthenticationToken anonymousAuth =
                new AnonymousAuthenticationToken("key", "anonymous",
                        List.of(new SimpleGrantedAuthority("ROLE_ANONYMOUS")));

        SecurityContext context = new SecurityContextImpl();
        context.setAuthentication(anonymousAuth);
        SecurityContextHolder.setContext(context);
    }
}
