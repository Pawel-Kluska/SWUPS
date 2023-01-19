package com.example.swups.repository;

import com.example.swups.entity.PlanStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PlanStatusRepositoryTest {

    @Autowired
    private PlanStatusRepository planstatusRepository;

    @Test
    void findByLoginSuccessTest() {
        // given
        String name = "name";

        PlanStatus planstatus = PlanStatus.builder()
                .name(name)
                .build();

        planstatusRepository.save(planstatus);

        // when
        PlanStatus planStatusByName = planstatusRepository.getPlanStatusByName(name);

        // then
        assertThat(planStatusByName).isNotNull();
        assertThat(planStatusByName.getName()).isEqualTo(name);
    }

    @Test
    void findByLoginFailTest() {

        //given

        String name = "name";

        // when
        PlanStatus planStatusByName = planstatusRepository.getPlanStatusByName(name);

        // then
        assertThat(planStatusByName).isNull();
    }

}
