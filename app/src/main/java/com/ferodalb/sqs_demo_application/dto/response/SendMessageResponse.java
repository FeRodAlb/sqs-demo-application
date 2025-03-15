package com.ferodalb.sqs_demo_application.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SendMessageResponse {

    @JsonProperty("messageId")
    private final String messageId;

    public SendMessageResponse(String messageId){
        this.messageId = messageId;
    }

    public String getMessageId(){
        return this.messageId;
    }
}
