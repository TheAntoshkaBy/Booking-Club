package com.epam.esm.exception;

import lombok.Data;

@Data
public class InvalidControllerOutputMessage {

    private String message;

    public InvalidControllerOutputMessage(String message) {
        this.message = message;
    }
}
