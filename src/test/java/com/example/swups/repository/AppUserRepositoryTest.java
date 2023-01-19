package com.example.swups.repository;

import com.example.swups.TestUtils;
import com.example.swups.entity.User;
import com.example.swups.entity.Authority;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @AfterEach
    void tearDown() {
        appUserRepository.deleteAll();
        authorityRepository.deleteAll();
    }

    @Test
    void findByLoginSuccessTest() {
        // given
        String login = "login";

        User user = TestUtils.getTestUser("Name");
        user.setLogin(login);

        authorityRepository.save(user.getAuthority());
        appUserRepository.save(user);

        // when
        Optional<User> userByLogin = appUserRepository.findByLogin(login);

        // then
        assertThat(userByLogin.isPresent()).isTrue();
    }

    @Test
    void findByLoginFailTest() {
        // given
        String login = "login";

        // when
        Optional<User> userByLogin = appUserRepository.findByLogin(login);

        // then
        assertThat(userByLogin.isPresent()).isFalse();
    }
}
