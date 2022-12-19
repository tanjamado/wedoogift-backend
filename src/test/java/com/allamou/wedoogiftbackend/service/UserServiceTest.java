package com.allamou.wedoogiftbackend.service;

import com.allamou.wedoogiftbackend.dto.UserBalanceResponse;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private UserService userService;

    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    public void setup() {
        user1 = new User();
        user1.setMealBalance(0);
        user1.setGiftBalance(0);
        user1.setFullName("Dominique Simon TEST");

        user2 = new User();
        user2.setMealBalance(566);
        user2.setGiftBalance(89.9);
        user2.setFullName("Renaud Duchamps TEST");

        user3 = new User();
        user3.setMealBalance(0);
        user3.setGiftBalance(2760.50);
        user3.setFullName("Rosemonde Beaumont TEST");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

    }

    @Test
    public void getsAllUsers() {
        assertThat(userService.getAllUsers().size()).isEqualTo(3);
    }


    @Test
    public void getsTotalBalanceOfUser() {
        //Resultat attendu
        UserBalanceResponse expected = new UserBalanceResponse();
        expected.setTotalBalance(566 + 89.9);
        expected.setGiftBalance(89.9);
        expected.setMealBalance(566);
        expected.setFullName("Renaud Duchamps TEST");

        User actual = userRepository.findByFullName("Renaud Duchamps TEST").get();

        assertThat(userService.getTotalBalance(actual.getIdUser()))
                .usingRecursiveComparison().isEqualTo(expected);
    }


}
