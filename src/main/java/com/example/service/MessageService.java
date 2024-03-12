package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.naming.AuthenticationException;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message) throws AuthenticationException
    {
        if(message.getMessage_text().length() > 0 && message.getMessage_text().length() <= 255)
        {
            return messageRepository.save(message);
        }

        throw new AuthenticationException("Message could not be created.");
    }

    public List<Message> getMessageList()
    {
        return (List<Message>)messageRepository.findAll();
    }

    public Message getMessageByID(int message_id)
    {
        return messageRepository.findById(message_id)
            .orElse(null);
    }

    public void deleteMessageByID(int message_id)
    {
        messageRepository.deleteById(message_id);
    }

    public void patchMessageByID(int message_id, String new_text) throws AuthenticationException
    {
        Message message = messageRepository.findById(message_id)
                .orElseThrow(() -> new AuthenticationException("Message was not found."));

        //check if the new text is valid (not blank and not over 255 characters)
        if(new_text.length() == 0 || new_text.length() > 255)
        {
            throw new AuthenticationException("New text is invalid");
        }

        message.setMessage_text(new_text);
        messageRepository.save(message);
    }

    public List<Message> getAllMessagesFromUser(int account_id)
    {
        return messageRepository.findByPostedBy(account_id);
    }
}
