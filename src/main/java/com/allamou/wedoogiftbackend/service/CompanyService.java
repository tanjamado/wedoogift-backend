package com.allamou.wedoogiftbackend.service;

import com.allamou.wedoogiftbackend.exception.CompanyNotFoundException;
import com.allamou.wedoogiftbackend.exception.IncorrectDepositTypeException;
import com.allamou.wedoogiftbackend.exception.NotEnoughBalanceException;
import com.allamou.wedoogiftbackend.exception.UserNotFoundException;
import com.allamou.wedoogiftbackend.model.Company;
import com.allamou.wedoogiftbackend.model.DEPOSIT_TYPE;
import com.allamou.wedoogiftbackend.model.Deposit;
import com.allamou.wedoogiftbackend.model.User;
import com.allamou.wedoogiftbackend.repository.CompanyRepository;
import com.allamou.wedoogiftbackend.repository.DepositRepository;
import com.allamou.wedoogiftbackend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);

    private final CompanyRepository companyRepository;
    private final DepositRepository depositRepository;
    private final UserRepository userRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, DepositRepository depositRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.depositRepository = depositRepository;
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(Company company) {
        return company != null ? userRepository.findAllByCompany(company) : new ArrayList<>();
    }

    public void makeDeposit(int companyId, int userId, double amount, DEPOSIT_TYPE depositType) {

        //On récupère l'entreprise concernée en bdd
        Company company = companyRepository.findById(companyId).orElseThrow(
                () -> new CompanyNotFoundException("Entreprise avec l'id " + companyId + " est introuvable en bdd."));

        //On calcule le nouveau solde de l'entreprise
        double newCompanyBalance = company.getBalance() - amount;

        //Si l'entreprise n'a pas assez de solde, une exception est levée
        if(newCompanyBalance < 0) {
            throw new NotEnoughBalanceException("Pas assez de solde pour l'entreprise. Solde actuel : " + company.getBalance());
        }

        //Màj du solde de l'entreprise
        companyRepository.updateBalance(companyId, newCompanyBalance);

        //On récupère l'utilisateur concerné de la bdd
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("Utilisateur avec l'id " + userId + " est introuvable en bdd."));

        //Création du dépot chez l'utilisateur concerné
        Deposit deposit = new Deposit();
        deposit.setDepositAmount(amount);
        deposit.setType(depositType);
        deposit.setUser(user);

        if(depositType.toString().equals("GIFT")) {

            //Ici et pour les Gift Deposit, on rajoute un an à la date actuelle.
            deposit.setExpirationDate(LocalDate.now().plusDays(365));

            //On calcule le nouveau solde GIFT de l'utilisateur
            double newGiftBalance = user.getGiftBalance() + amount;

            //On met à jour le solde GIFT de l'utilisateur en bdd
            userRepository.updateGiftBalance(userId, newGiftBalance);

        } else if(depositType.toString().equals("MEAL")) {

            //Ici et pour les Meal Deposit, on rajoute un an à la date actuelle et on fixe la date au 59e jour de l'année
            //ce qui correspond au 28 février
            deposit.setExpirationDate(LocalDate.now().plusYears(1).withDayOfYear(59));

            //On calcule le nouveau solde MEAL de l'utilisateur
            double newMealBalance = user.getMealBalance() + amount;

            //On met à jour le solde MEAL de l'utilisateur en bdd
            userRepository.updateMealBalance(userId, newMealBalance);

        } else {
            //Si le type de dépot ne correspond à ni GIFT ni MEAL => Levage d'exception
            throw new IncorrectDepositTypeException("Type de Deposit : " + depositType + " incorrect, merci " +
                    "de choisir soit GIFT ou MEAL.");
        }

        //Finalisation de l'opération : Création du dépôt en bdd
        depositRepository.save(deposit);
    }






}
