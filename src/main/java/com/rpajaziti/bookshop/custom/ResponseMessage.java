package com.rpajaziti.bookshop.custom;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseMessage {
    @JsonProperty
    private String message;

    public String getMessage() {
        return message;
    }

    public ResponseMessage setMessage(String message) {
        this.message = message;
        return this;
    }
}
