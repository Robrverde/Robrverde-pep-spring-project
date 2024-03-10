package com.example.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.AuthenticationException;

import com.example.entity.Account;
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

    public void register(Account newAccount)
    {
        accountRepository.save(newAccount);
    }

    //TODO: Custome Query for login
    public void login(String username, String password) throws AuthenticationException
    {
        
    }
}
