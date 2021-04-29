package com.epam.esm.service.validator;

import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.exception.constant.ErrorTextMessageConstants;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    public void checkId(Long id) {
        if (id <= 0) {
            throw new ServiceValidationException(
                new InvalidDataMessage(ErrorTextMessageConstants.ORDER_ID));
        }
    }
}
