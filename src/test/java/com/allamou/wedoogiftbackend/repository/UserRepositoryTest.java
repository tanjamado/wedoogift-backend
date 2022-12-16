package com.allamou.wedoogiftbackend.repository;

import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void savesUser() {

        User expectedUser = new User();
        expectedUser.setFullName("OthmaneTest AllamouTest");
        expectedUser.setMealBalance(0);
        expectedUser.setGiftBalance(0);

        User actualUser = userRepository.save(expectedUser);

        assertThat(actualUser).usingRecursiveComparison().isEqualTo(expectedUser);

    }

    @Test
    public void findsUserByCompany() {

        Company company = new Company();
        company.setName("wedoogift test");
        company.setBalance(9999);

    }


}
