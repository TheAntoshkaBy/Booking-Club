package com.epam.esm.exception.entity;

import lombok.Data;

@Data
public class InvalidDataOutputMessage {

    private String message;

    public InvalidDataOutputMessage(String message) {
        this.message = message;
    }
}
