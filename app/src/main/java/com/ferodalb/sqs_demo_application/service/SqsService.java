package com.ferodalb.sqs_demo_application.service;

import io.awspring.cloud.sqs.operations.SendResult;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.model.SqsException;

@Service
public class SqsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SqsService.class);

    private SqsTemplate sqsTemplate;

    @Value("${aws.sqs.queueUrl}")
    private String queueUrl;

    public SqsService(SqsTemplate sqsTemplate){
        this.sqsTemplate = sqsTemplate;
    }

    public SendResult<String> sendMessage(String message){

        try {

            SendResult<String> response = sqsTemplate.send(queueUrl, message);

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
