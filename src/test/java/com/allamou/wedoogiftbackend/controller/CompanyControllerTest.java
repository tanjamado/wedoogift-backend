package com.allamou.wedoogiftbackend.controller;

import com.allamou.wedoogiftbackend.exception.NotEnoughBalanceException;
import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.DEPOSIT_TYPE;
import com.allamou.wedoogiftbackend.model.Deposit;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.CompanyRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import com.allamou.wedoogiftbackend.service.CompanyService;
import com.allamou.wedoogiftbackend.service.DepositService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:application-test.properties")
public class CompanyControllerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @SpyBean
    private CompanyService companyService;

    @SpyBean
    private DepositService depositService;

    @SpyBean
    private CompanyController companyController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private Company company;
    private User user;

    private static final double ORIGINAL_COMPANY_BALANCE = 2000;



    @BeforeEach
    public void setup() {
        user = new User();
        user.setMealBalance(233);
        user.setGiftBalance(22);
        user.setFullName("USER 1");

        company = new Company();
        company.setName("TEST COMPANY");
        company.setBalance(ORIGINAL_COMPANY_BALANCE);
        company.addUser(user, true);

        companyRepository.save(company);
        userRepository.save(user);
    }

    @Test
    public void makesGiftDepositToUser() {

        int companyId = company.getCompanyId();
        int userId = user.getIdUser();

        companyController.makeDepositToUser(companyId, userId, 300, String.valueOf(DEPOSIT_TYPE.GIFT));

        List<Deposit> result = depositService.getDepositsOfUser(userId);

        entityManager.clear();

        assertThat(result.get(0)).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDepositAmount()).isEqualTo(300);
        assertThat(companyRepository.findByName("TEST COMPANY").get().getBalance())
                .isEqualTo(ORIGINAL_COMPANY_BALANCE - result.get(0).getDepositAmount());
        assertThat(result.get(0).getExpirationDate()).isEqualTo(LocalDate.now().plusYears(1));
    }

    @Test
    public void makesMealDepositToUser() {

        int companyId = company.getCompanyId();
        int userId = user.getIdUser();

        companyController.makeDepositToUser(companyId, userId, 600, String.valueOf(DEPOSIT_TYPE.MEAL));

        List<Deposit> result = depositService.getDepositsOfUser(userId);

        entityManager.clear();

        assertThat(result.get(0)).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDepositAmount()).isEqualTo(600);
        assertThat(companyRepository.findByName("TEST COMPANY").get().getBalance()).isEqualTo(ORIGINAL_COMPANY_BALANCE - result.get(0).getDepositAmount());
        assertThat(result.get(0).getExpirationDate()).isEqualTo(LocalDate.now().plusYears(1).withDayOfYear(59));
    }

    @Test
    public void makesDepositWithNotEnoughBalance() {
        int companyId = company.getCompanyId();
        int userId = user.getIdUser();
        assertThatThrownBy(()->companyController.makeDepositToUser(companyId, userId, 2500, String.valueOf(DEPOSIT_TYPE.GIFT)))
                .isInstanceOf(NotEnoughBalanceException.class)
                .hasMessageContaining("Pas assez de solde pour l'entreprise");
    }

}
