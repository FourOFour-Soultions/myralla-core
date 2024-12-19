package com.myralla.loyalty.Controllers;

import com.myralla.loyalty.Services.AccountService;
import com.myralla.loyalty.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/api/account")
@RestController
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(path = "createAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.createAccount(accountDTO);
    }
}
