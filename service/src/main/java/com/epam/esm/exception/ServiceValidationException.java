package com.epam.esm.exception;

import java.util.List;

public class ServiceValidationException extends ServiceBadRequestException {

    public ServiceValidationException(List<InvalidDataMessage> messages) {
        super(messages);
    }

    public ServiceValidationException(InvalidDataMessage message) {
        super(message);
    }
}
