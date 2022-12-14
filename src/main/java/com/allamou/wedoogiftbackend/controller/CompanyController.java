package com.allamou.wedoogiftbackend.controller;


import com.allamou.wedoogiftbackend.model.DEPOSIT_TYPE;
import com.allamou.wedoogiftbackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<Void> makeDepositToUser(@RequestParam("company_id") int companyId,
                                                  @RequestParam("user_id") int userId,
                                                  @RequestParam double amount,
                                                  @RequestParam("type") String depositType) {
        companyService.makeDeposit(companyId, userId, amount, DEPOSIT_TYPE.valueOf(depositType));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
