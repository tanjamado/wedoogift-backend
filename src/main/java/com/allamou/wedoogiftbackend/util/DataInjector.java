package com.allamou.wedoogiftbackend.util;

import com.allamou.wedoogiftbackend.repository.CompanyRepository;
import com.allamou.wedoogiftbackend.repository.DepositRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
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

    }
}
