package com.example.BankProject.BankApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.BankProject.BankApplication.Entity.Account;
import com.example.BankProject.BankApplication.Services.AccountService;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = service.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable Long accountNumber) {
        Account account = service.getAccountDetailsByAccountNumber(accountNumber);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> accounts = service.getAllAccountDetails();
        return ResponseEntity.ok(accounts);
    }
    @PutMapping("/deposit/{accountNumber}/{amount}")
    public Account depositAccount(@PathVariable Long accountNumber, @PathVariable Double amount) {
        Account account = service.depositAmount(accountNumber,amount);
        return account; 
    	}
    @PutMapping("/withdraw/{accountNumber}/{amount}")
    public Account withdrawAccount(@PathVariable Long accountNumber,@PathVariable Double amount) {
    Account account = service.withdrawAmount(accountNumber, amount);
    	return account;
    }
    @DeleteMapping("{accountNumber}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long accountNumber){
    	service.closeAccount(accountNumber);
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Account closed");
    	
    	
    }
    }
