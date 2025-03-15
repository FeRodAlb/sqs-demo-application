package com.ferodalb.sqs_demo_application.controller;

import com.ferodalb.sqs_demo_application.dto.request.MessageRequest;
import com.ferodalb.sqs_demo_application.dto.response.DefaultResponse;
import com.ferodalb.sqs_demo_application.dto.response.SendMessageResponse;
import com.ferodalb.sqs_demo_application.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public DefaultResponse<SendMessageResponse> postMessage(@Valid @RequestBody MessageRequest message){

        SendMessageResponse response = messageService.send(message);
        return new DefaultResponse<>(response);
    }

}
