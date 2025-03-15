package com.ferodalb.sqs_demo_application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ferodalb.sqs_demo_application.dto.request.MessageRequest;
import com.ferodalb.sqs_demo_application.dto.response.SendMessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MessageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageService.class);
    private SqsService sqsService;
    private ObjectMapper objectMapper;

    public MessageService(SqsService sqsService, ObjectMapper objectMapper){
        this.sqsService = sqsService;
        this.objectMapper = objectMapper;
    }

    public SendMessageResponse send(MessageRequest message) {

        if (Objects.isNull(message)) {
            LOGGER.atError().setMessage("The message is null").log();
            throw new IllegalArgumentException("Message cannot be null.");
        }

        try {
            String json = objectMapper.writeValueAsString(message);
            software.amazon.awssdk.services.sqs.model.SendMessageResponse response = sqsService.sendMessage(json);
            LOGGER.atInfo().setMessage("Message sent successfully. Message: {}. MessageId: {}")
                    .addArgument(json)
                    .addArgument(response.messageId())
                    .log();
            return new SendMessageResponse(response.messageId());
        } catch (JsonProcessingException ex){
            LOGGER.atError().setMessage("Failed to serialize message to JSON. Error: {}")
                    .addArgument(ex.getMessage())
                    .setCause(ex)
                    .log();
            throw new RuntimeException();
        } catch (Exception ex) {
            LOGGER.atError().setMessage("Unexpected error occurred while sending message: {}")
                    .addArgument(ex.getMessage())
                    .setCause(ex)
                    .log();
            throw ex;
        }
    }
}
