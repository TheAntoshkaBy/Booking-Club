package com.epam.esm.exception;

import com.epam.esm.exception.entity.InvalidDataOutputMessage;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;

public class RepositoryNotFoundException extends RuntimeException {

    private final static String MESSAGE = "Repository exception";
    private List<InvalidDataOutputMessage> messages;
    private InvalidDataOutputMessage message;

    public RepositoryNotFoundException(List<InvalidDataOutputMessage> messages) {
        this.messages = messages;
    }

    public RepositoryNotFoundException() {
    }

    public RepositoryNotFoundException(InvalidDataOutputMessage message) {
        this.message = message;
        messages = new ArrayList<>();
        messages.add(message);
    }

    public List<InvalidDataOutputMessage> getMessages() {
        return messages;
    }

    public InvalidDataOutputMessage getErrorMessage() {
        return message;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

    public String getStatus(){
        return HttpStatus.NOT_FOUND.toString();
    }
}
