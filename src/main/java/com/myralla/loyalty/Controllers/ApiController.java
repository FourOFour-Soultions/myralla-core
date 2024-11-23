package com.myralla.loyalty.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myralla.loyalty.Services.AccountService;

import java.util.Map;

@RequestMapping("/v1/api")
@RestController
public class ApiController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "testGet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> testGet() {
        return ResponseEntity.ok(Map.of("message", "testGet success"));
    }

    @PostMapping(path = "createAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createAccount(@RequestBody Map<String, String> body) {
       return accountService.linkAccount(body);
    }

    @PostMapping(path = "registerTenant", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> registerTenant(@RequestBody Map<String, String> body) throws Exception{
        return accountService.createTenant(body);
    }
}