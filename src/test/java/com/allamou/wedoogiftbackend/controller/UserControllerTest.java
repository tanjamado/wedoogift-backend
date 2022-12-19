package com.allamou.wedoogiftbackend.controller;

import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import com.allamou.wedoogiftbackend.service.CompanyService;
import com.allamou.wedoogiftbackend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:application-test.properties")
public class UserControllerTest {

    @SpyBean
    private CompanyService companyService;

    @SpyBean
    private UserService userService;

    @SpyBean
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setup() {

        user1 = new User();
        user1.setMealBalance(233);
        user1.setGiftBalance(22);
        user1.setFullName("USER 1");

        user2 = new User();
        user2.setMealBalance(987);
        user2.setGiftBalance(12.99);
        user2.setFullName("USER 2");

        user3 = new User();
        user3.setMealBalance(0);
        user3.setGiftBalance(344);
        user3.setFullName("USER 3");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }

    @Test
    public void getsAllUsers() {
        assertThat(userController.getAllUsers().getBody().size()).isEqualTo(3);
    }

    @Test
    public void getsTotalBalance() {
        assertThat(userController.getTotalBalance(user1.getIdUser()).getBody().getTotalBalance()).isEqualTo(255);
        assertThat(userController.getTotalBalance(user2.getIdUser()).getBody().getTotalBalance()).isEqualTo(987 + 12.99);
        assertThat(userController.getTotalBalance(user3.getIdUser()).getBody().getTotalBalance()).isEqualTo(344);
    }
}
