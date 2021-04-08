package com.epam.esm.exception;

import com.epam.esm.pojo.InvalidDataMessage;
import java.util.List;

public class ServiceValidationException extends ServiceBadRequestException {

    public ServiceValidationException(List<InvalidDataMessage> messages) {
        super(messages);
    }

    public ServiceValidationException(InvalidDataMessage message) {
        super(message);
    }
}
