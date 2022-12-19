package com.allamou.wedoogiftbackend.repository;


import com.allamou.wedoogiftbackend.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations="classpath:application-test.properties")
public class CompanyRespositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void findsCompanyByName() {
       Company company = new Company();
       company.setName("test company");
       company.setBalance(9999);

       companyRepository.save(company);

       assertThat(companyRepository.findByName("test company")).isNotNull();
    }

}
