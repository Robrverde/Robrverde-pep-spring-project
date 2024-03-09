package com.example.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.Message;
import com.example.exception.ResourceNotFoundException;


@Service
public class MessageService {
    private List<Message> messageList = new ArrayList<>();

    public void addMessage(Message message)
    {
        messageList.add(message);
    }

    public List<Message> getMessageList()
    {
        return messageList;
    }

    //GET localhost:8080/messages/{message_id}
    public Message getMessageByID(int message_id) throws ResourceNotFoundException
    {
        for(Message message : messageList)
        {
            if(message.getMessage_id().equals(message_id))
                return message;
        }

        throw new ResourceNotFoundException("Message not found");
    }

    //DELETE localhost:8080/messages/{message_id}
    public void deleteMessageByID(int message_id)
    {
        messageList.removeIf(message -> message.getMessage_id().equals(message_id));
    }

    //PATCH localhost:8080/messages/{message_id}
    public void patchMessageByID(int message_id, String new_text) throws ResourceNotFoundException
    {
        for(Message message : messageList)
        {   
            //check if message exists
            if(message.getMessage_id().equals(message_id))
            {
                //check if the new text is valid (not blank and not over 255 characters)
                if(new_text.length() > 0 && new_text.length() <= 255)
                    message.setMessage_text(new_text);
                
                return;
            }
        }

        throw new ResourceNotFoundException("Message not found");
    }
}
