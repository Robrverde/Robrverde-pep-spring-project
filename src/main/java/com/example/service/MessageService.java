package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository)
    {
        this.messageRepository = messageRepository;
    }

    public void addMessage(Message message)
    {
        messageRepository.save(message);
    }

    public List<Message> getMessageList()
    {
        return (List<Message>)messageRepository.findAll();
    }

    //GET localhost:8080/messages/{message_id}
    public Message getMessageByID(int message_id) throws ResourceNotFoundException
    {
        return messageRepository.findById(message_id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }

    //DELETE localhost:8080/messages/{message_id}
    public void deleteMessageByID(int message_id)
    {
        messageRepository.deleteById(message_id);
    }

    //PATCH localhost:8080/messages/{message_id}
    public void patchMessageByID(int message_id, String new_text) throws ResourceNotFoundException
    {
        Message message = messageRepository.findById(message_id)
                .orElseThrow(() -> new ResourceNotFoundException("Message not found"));

        //check if the new text is valid (not blank and not over 255 characters)
        if(new_text.length() > 0 && new_text.length() <= 255)
            message.setMessage_text(new_text);
        
        messageRepository.save(message);
    }
}
