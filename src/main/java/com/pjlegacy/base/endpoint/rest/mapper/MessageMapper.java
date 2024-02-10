package com.pjlegacy.base.endpoint.rest.mapper;


import com.pjlegacy.base.endpoint.rest.model.MessageRest;
import com.pjlegacy.base.model.User;
import com.pjlegacy.base.model.chatModel.MessageModel;
import com.pjlegacy.base.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class MessageMapper {

    private final UserRepository userRepository;

    public MessageModel toRest(Optional<MessageModel> model){

        return model.isPresent() ? MessageModel.builder()
                .message(model.get().getMessage())
                .messageDate(model.get().getMessageDate())
                .id(model.get().getId())
                .receiver(model.get().getReceiver())
                .sender(model.get().getSender())
                .build() : new MessageModel();
    }

    public MessageModel toModel(MessageRest rest){
        User sender = userRepository.getById(Objects.requireNonNull(rest.getIdSender()));
        User receiver = userRepository.getById(Objects.requireNonNull(rest.getIdReceiver()));

        return MessageModel.builder()
                .sender(sender)
                .message(rest.getMessage())
                .receiver(receiver)
                .id(rest.getId())
                .build();
    }
}
