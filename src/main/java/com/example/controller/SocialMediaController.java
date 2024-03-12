package com.example.controller;

import java.util.List;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.DuplicateUsernameFoundException;
import com.example.exception.ResourceNotFoundException;
import com.example.service.AccountService;
import com.example.service.MessageService;


/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService)
    {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    public ResponseEntity<Account> register(@RequestBody Account account) throws AuthenticationException, DuplicateUsernameFoundException
    {
        Account created = accountService.register(account);
        return ResponseEntity.status(HttpStatus.OK)
                .body(created);
    }

    @PostMapping("login")
    public ResponseEntity<Account> login(@RequestBody Account account) throws ResourceNotFoundException
    {
        Account found = accountService.login(account.getUsername(), account.getPassword());

        return ResponseEntity.status(HttpStatus.OK)
                .body(found);
    }

    //Create a message
    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) throws AuthenticationException
    {
        //check if the account creating the message exists, and returns it
        accountService.getAccountById(message.getPosted_by());

        //create a message object it a new message was successfuly created
        Message created = messageService.addMessage(message);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    //Retrieve all messages
    @GetMapping("messages")
    public List<Message> getAllMessages()
    {
        return messageService.getMessageList();
    }

    //Get message by ID
    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int message_id)
    {
        return ResponseEntity.status(HttpStatus.OK)
            .body(messageService.getMessageByID(message_id));
    }

    //Delete message by ID
    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessageById(@PathVariable int message_id)
    {
        Message message =  messageService.getMessageByID(message_id);

        if(message != null)
        {
            messageService.deleteMessageByID(message_id);
            return ResponseEntity.status(HttpStatus.OK)
                .body(1);
        }

        return ResponseEntity.status(HttpStatus.OK)
            .body(null);
    }

    //Update message by ID
    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> patchMessage(@PathVariable int message_id, @RequestBody Message message) throws AuthenticationException
    {
        messageService.patchMessageByID(message_id, message.getMessage_text());

        return ResponseEntity.status(HttpStatus.OK)
            .body(1);
    }

    //Get all messages from a specific user
    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getAllMessagesFromAccount(@PathVariable("account_id") int account_id)
    {
        List<Message> messageList = messageService.getAllMessagesFromUser(account_id);

        return ResponseEntity.status(HttpStatus.OK).body(messageList);
    }
}
