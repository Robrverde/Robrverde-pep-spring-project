package com.example.service;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private List<Account> accountList = new ArrayList<>();

    private MessageService messageService;

    //Dependency injection of Message Service
    @Autowired
    public AccountService(MessageService messageService)
    {
        this.messageService = messageService;
    }

    public void register(Account newAccount)
    {
        accountList.add(newAccount);
    }
}
