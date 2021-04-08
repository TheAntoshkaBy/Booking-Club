package com.epam.esm.exception;

import com.epam.esm.exception.entity.InvalidDataOutputMessage;
import java.util.List;

public class NotSupportedOperationNotFoundException extends RepositoryNotFoundException {

    public NotSupportedOperationNotFoundException(List<InvalidDataOutputMessage> messages) {
        super(messages);
    }

    public NotSupportedOperationNotFoundException() {
        super();
    }

    public NotSupportedOperationNotFoundException(InvalidDataOutputMessage message) {
        super(message);
    }

    @Override
    public List<InvalidDataOutputMessage> getMessages() {
        return super.getMessages();
    }

    @Override
    public InvalidDataOutputMessage getErrorMessage() {
        return super.getErrorMessage();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
