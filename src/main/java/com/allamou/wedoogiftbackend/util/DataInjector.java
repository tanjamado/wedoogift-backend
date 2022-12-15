package com.allamou.wedoogiftbackend.util;

import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.CompanyRepository;
import com.allamou.wedoogiftbackend.repository.DepositRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import com.allamou.wedoogiftbackend.service.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInjector implements CommandLineRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(DataInjector.class);

    private final UserRepository userRepository;

    private final CompanyRepository companyRepository;

    private final DepositRepository depositRepository;

    @Autowired
    public DataInjector(UserRepository userRepository, CompanyRepository companyRepository, DepositRepository depositRepository) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.depositRepository = depositRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Chargement de données ...");

        //Pour simplifier, on part du principe qu'il y a au moins une entreprise lié à au moins un utilisateur avant de lancer l'application
        if(companyRepository.findAll().isEmpty() && userRepository.findAll().isEmpty()) {

            User user1 = new User();
            user1.setMealBalance(0);
            user1.setGiftBalance(0);
            user1.setFullName("Dominique Simon");

            User user2 = new User();
            user2.setMealBalance(100);
            user2.setGiftBalance(0);
            user2.setFullName("Renaud Duchamps");

            User user3 = new User();
            user3.setMealBalance(0);
            user3.setGiftBalance(240.50);
            user3.setFullName("Rosemonde Beaumont");

            User user4 = new User();
            user4.setMealBalance(188.99);
            user4.setGiftBalance(2.56);
            user4.setFullName("Othmane Allamou");

            Company company = new Company();
            company.setName("company A");
            company.setBalance(1000.23);
            company.addUser(user1, true);
            company.addUser(user2, true);
            company.addUser(user3, true);
            company.addUser(user4, true);

            companyRepository.save(company);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);

        }

        LOGGER.info("Chargement des données terminé.");

    }
}
