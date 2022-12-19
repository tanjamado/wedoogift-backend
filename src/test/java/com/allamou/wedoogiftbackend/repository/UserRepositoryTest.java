package com.allamou.wedoogiftbackend.repository;

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
    public void findsByFullName() {
        User user = new User();
        user.setFullName("OthmaneTest AllamouTest");
        user.setMealBalance(0);
        user.setGiftBalance(0);

        userRepository.save(user);

        assertThat(userRepository.findByFullName("OthmaneTest AllamouTest").get())
                .usingRecursiveComparison().isEqualTo(user);
    }


    //etc ..


}
