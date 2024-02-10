package com.pjlegacy.base.service;

import com.pjlegacy.base.model.User;
import com.pjlegacy.base.model.chatModel.MessageModel;
import com.pjlegacy.base.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    private final UserService userService;

    public MessageModel getByUserId(String senderId, String receiverId){
        User sender = userService.getUserById(senderId);
        User receiver = userService.getUserById(receiverId);

        return repository.findMessageModelBySenderIdAndReceiverId(sender.getId(),receiver.getId());
    }

    public MessageModel postMessage(MessageModel messageModel){
        return repository.save(messageModel);
    }
}
