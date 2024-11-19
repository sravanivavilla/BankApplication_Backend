package com.example.BankProject.BankApplication.Services;

import java.util.List;

import com.example.BankProject.BankApplication.Entity.Account;

public interface AccountService {
    Account createAccount(Account account);
    Account getAccountDetailsByAccountNumber(Long accountNumber);
    List<Account> getAllAccountDetails(); // Implemented in AccountServiceImpl
    Account depositAmount(Long accountNumber, Double amount);
    Account withdrawAmount(Long accountNumber, Double amount); // Fixed typo in method name
    void closeAccount(Long accountNumber); // Fixed typo in method name
}