package com.ferodalb.sqs_demo_application.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("field")
    private String field;

    @JsonProperty("message")
    private String message;

    public ErrorResponse(String field, String message){
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
