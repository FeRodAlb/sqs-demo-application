package com.ferodalb.sqs_demo_application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DefaultResponse<T> {

    @JsonProperty("data")
    private T data;

    @JsonProperty("errors")
    private List<ErrorResponse> errors;

    public DefaultResponse(T data){
        this.data = data;
    }

    public DefaultResponse(List<ErrorResponse> errors){
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }
}
