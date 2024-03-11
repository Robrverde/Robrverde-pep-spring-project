package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameFoundException;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private AccountRepository accountRepository;

    private MessageService messageService;

    //Dependency injection of Message Service
    @Autowired
    public AccountService(MessageService messageService, AccountRepository accountRepository)
    {
        this.messageService = messageService;
        this.accountRepository = accountRepository;
    }

    public void register(Account newAccount) throws AuthenticationException, DuplicateUsernameFoundException
    {
        //check if the account is valid
        if(newAccount.getUsername().length() == 0 || newAccount.getPassword().length() < 4)
            throw new AuthenticationException("Invalid Username or Password.");
        
        //check if an account with the same Username already exist
        Account account = accountRepository.findByUsername(newAccount.getUsername())
                        .orElse(null);
        
        if(account != null)
            throw new DuplicateUsernameFoundException("There already exists an account with the username: " + newAccount.getUsername());
        
        accountRepository.save(newAccount);
    }

    public void login(String username, String password) throws AuthenticationException
    {
        accountRepository.findByUsernameAndPassword(username, password);
    }


}
