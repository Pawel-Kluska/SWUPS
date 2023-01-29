package com.example.swups;

import com.example.swups.entity.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.List;

public class TestUtils {

    public static User getTestUser(String name) {
        Authority authority = Authority.builder()
                .name("ROLE")
                .build();

        User user = User.builder()
                .name(name)
                .surname("surname")
                .login(name)
                .password("password")
                .authority(authority)
                .build();

        return user;
    }

    public static void logInUser(String role) {

        Authority authority = Authority.builder()
                .name(role)
                .build();

        UserDetails user = User.builder()
                .login(role)
                .name("name")
                .surname("surname")
                .password("password")
                .authority(authority)
                .build();

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

    public static PlanOfStudies getPlanOfStudies() {

        PlanStatus planStatus = PlanStatus.builder()
                .name("name")
                .build();

        FormOfStudy formOfStudy = FormOfStudy.builder()
                .name("name")
                .build();

        Degree degree = Degree.builder()
                .name("Name")
                .build();

        Faculty faculty = Faculty.builder()
                .shortName("shortname")
                .name("name")
                .build();

        Discipline discipline = Discipline.builder()
                .name("name")
                .build();

        FieldOfStudy fieldOfStudy = FieldOfStudy.builder()
                .shortname("shorname")
                .name("name")
                .faculty(faculty)
                .discipline(discipline)
                .build();

        Profile profile = Profile.builder()
                .name("name")
                .build();

        EducationLevel educationLevel = EducationLevel.builder()
                .name("name")
                .build();

        PlanOfStudies planOfStudies = PlanOfStudies.builder()
                .id(1)
                .identifier("id1")
                .planStatus(planStatus)
                .formOfStudy(formOfStudy)
                .dateOfCreation(Instant.now())
                .degree(degree)
                .fieldOfStudy(fieldOfStudy)
                .languageOfStudy("language")
                .educationCycle("educationCycle")
                .educationLevel(educationLevel)
                .profile(profile)
                .specialization("specialization")
                .build();
        return planOfStudies;
    }
}
