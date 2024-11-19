package com.example.BankProject.BankApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.BankProject.BankApplication.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>{

}
