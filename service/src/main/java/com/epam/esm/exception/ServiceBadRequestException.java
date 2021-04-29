package com.epam.esm.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.http.HttpStatus;

public class ServiceBadRequestException extends RuntimeException {

    private final static String MESSAGE = "Service exception";
    private final List<InvalidDataMessage> messages;

    public ServiceBadRequestException(List<InvalidDataMessage> messages) {
        this.messages = messages;
    }

    public ServiceBadRequestException(InvalidDataMessage message) {
        messages = new ArrayList<>();
        messages.add(message);
    }

    public List<InvalidDataMessage> getMessages() {
        return messages;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public String getStatus(){
        return HttpStatus.BAD_REQUEST.toString();
    }
}
