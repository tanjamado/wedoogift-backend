package com.allamou.wedoogiftbackend.service;

import com.allamou.wedoogiftbackend.exception.NotEnoughBalanceException;
import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.DEPOSIT_TYPE;
import com.allamou.wedoogiftbackend.model.Deposit;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.CompanyRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyServiceTest {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @SpyBean
    private CompanyService companyService;

    @SpyBean
    private DepositService depositService;

    private Company company;
    private User user1;
    private User user2;

    private static final double ORIGINAL_COMPANY_BALANCE = 1000;

    @BeforeEach
    public void setup() {
        user1 = new User();
        user1.setMealBalance(0);
        user1.setGiftBalance(555);
        user1.setFullName("USER 1");

        user2 = new User();
        user2.setMealBalance(666);
        user2.setGiftBalance(0.99);
        user2.setFullName("USER 2");

        company = new Company();
        company.setName("TEST COMPANY");
        company.setBalance(ORIGINAL_COMPANY_BALANCE);
        company.addUser(user1, true);
        company.addUser(user2, true);

        companyRepository.save(company);

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @Test
    public void getsAllUserOfCompany() {
        assertThat(companyService.getAllUsers(company).size()).isEqualTo(2);
    }

    @Test
    public void makesGiftDepositToUser() {

        int companyId = company.getCompanyId();
        int userId = user1.getIdUser();

        companyService.makeDeposit(companyId, userId, 500, DEPOSIT_TYPE.GIFT);

        List<Deposit> result = depositService.getDepositsOfUser(userId);

        //On vide le cache pour récupérer les données à jour de la bdd
        entityManager.clear();

        assertThat(result.get(0)).isNotNull();
        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getDepositAmount()).isEqualTo(500);
        assertThat(companyRepository.findByName("TEST COMPANY").get().getBalance()).isEqualTo(ORIGINAL_COMPANY_BALANCE - result.get(0).getDepositAmount());
        assertThat(result.get(0).getExpirationDate()).isEqualTo(LocalDate.now().plusYears(1));
    }

    @Test
    public void makesMealDepositToUser() {

        int companyId = company.getCompanyId();
        int userId = user2.getIdUser();

        companyService.makeDeposit(companyId, userId, 600, DEPOSIT_TYPE.MEAL);

        List<Deposit> result = depositService.getDepositsOfUser(userId);

        //On vide le cache pour récupérer les données à jour de la bdd
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
        int userId = user2.getIdUser();
        assertThatThrownBy(() -> companyService.makeDeposit(companyId, userId, 1100, DEPOSIT_TYPE.GIFT))
                .isInstanceOf(NotEnoughBalanceException.class)
                .hasMessageContaining("Pas assez de solde pour l'entreprise");
    }
}
