package com.ferodalb.sqs_demo_application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Service
public class SqsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SqsService.class);

    private SqsClient sqsClient;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    public SqsService(SqsClient sqsClient){
        this.sqsClient = sqsClient;
    }

    public SendMessageResponse sendMessage(String message){

        try {
            SendMessageResponse response = sqsClient.sendMessage(SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(message)
                    .delaySeconds(10)
                    .build());

            LOGGER.atInfo().setMessage("Message sent successfully. MessageId: {}")
                    .addArgument(response.messageId())
                    .log();
            return response;
        }catch (SqsException ex){
            LOGGER.atError().setMessage("Failed to send message to SQS. Error: {}. Exception: {}")
                    .addArgument(ex.awsErrorDetails().errorMessage())
                    .addArgument(ex.getMessage())
                    .log();
            throw ex;
        }catch (Exception ex){
            LOGGER.atError().setMessage("Unexpected error occurred while sending message to SQS. Error: {}. Exception: {}")
                    .addArgument(ex.getMessage())
                    .addArgument(ex)
                    .log();
            throw ex;
        }
    }
}
