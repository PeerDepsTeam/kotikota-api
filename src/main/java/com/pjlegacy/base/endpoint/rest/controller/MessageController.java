package com.pjlegacy.base.endpoint.rest.controller;

import com.pjlegacy.base.endpoint.rest.mapper.MessageMapper;
import com.pjlegacy.base.endpoint.rest.model.MessageRest;
import com.pjlegacy.base.model.chatModel.MessageModel;
import com.pjlegacy.base.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@Controller
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService service;
    private final MessageMapper mapper;

    @GetMapping
    public MessageModel getAllMessage(@RequestParam String senderId,
                                      @RequestParam String receiverId
                                      ){
        return service.getByUserId(senderId,receiverId);
    }

    @PostMapping
    public MessageModel postMessage(@RequestBody MessageRest messageRest){
        MessageModel model = mapper.toModel(messageRest);
        service.postMessage(model);
        return model;
    }
}
