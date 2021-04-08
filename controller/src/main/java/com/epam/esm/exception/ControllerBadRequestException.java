package com.epam.esm.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ControllerBadRequestException extends RuntimeException {

    private final static String MESSAGE = "Controller exception";
    private List<InvalidControllerOutputMessage> messages;
    private InvalidControllerOutputMessage message;

    public ControllerBadRequestException(List<InvalidControllerOutputMessage> messages) {
        this.messages = messages;
    }

    public ControllerBadRequestException() {
    }

    public ControllerBadRequestException(InvalidControllerOutputMessage message) {
        this.message = message;
        messages = new ArrayList<>();
        messages.add(message);
    }

    public List<InvalidControllerOutputMessage> getMessages() {
        return messages;
    }

    public InvalidControllerOutputMessage getErrorMessage() {
        return message;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public String getStatus(){
        return HttpStatus.BAD_REQUEST.toString();
    }
}
