package com.example.BankProject.BankApplication.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BankProject.BankApplication.Entity.Account;
import com.example.BankProject.BankApplication.Repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository repo;

    @Override
    public Account createAccount(Account account) {
        return repo.save(account);
    }

    @Override
    public Account getAccountDetailsByAccountNumber(Long accountNumber) {
        Optional<Account> account = repo.findById(accountNumber);
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<Account> getAllAccountDetails() {
        return repo.findAll(); // Implemented method to fetch all account details
    }

    @Override
    public Account depositAmount(Long accountNumber, Double amount) {
        // Fetch the account using findById
        Optional<Account> account = repo.findById(accountNumber);

        // If account is not found, throw an exception
        if (account.isEmpty()) {
            throw new RuntimeException("Account is not present");
        }

        // Get the account details
        Account accountPresent = account.get();

        // Calculate the new balance
        Double totalBalance = accountPresent.getAccount_balance() + amount;

        // Update the account balance
        accountPresent.setAccount_balance(totalBalance);

        // Save the updated account to the database
        repo.save(accountPresent);

        // Return the updated account
        return accountPresent;
    }
    @Override
    public Account withdrawAmount(Long accountNumber, Double amount) {
        // Fetch the account from the repository
        Optional<Account> account = repo.findById(accountNumber);
        System.out.println("control reached here");

        // If account is not found, throw an exception
        if (account.isEmpty()) {
            throw new RuntimeException("Account not found");
        }

        // Get the account details
        Account accountPresent = account.get();

        // Check if the account has sufficient balance
        if (accountPresent.getAccount_balance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Subtract the amount from the account balance
        accountPresent.setAccount_balance(accountPresent.getAccount_balance() - amount);

        // Save the updated account to the repository
        repo.save(accountPresent);

        // Return the updated account
        return accountPresent;
    
    }
    @Override
    public void closeAccount(Long accountNumber) {
        getAccountDetailsByAccountNumber(accountNumber);
        repo.deleteById(accountNumber);
    }
}
