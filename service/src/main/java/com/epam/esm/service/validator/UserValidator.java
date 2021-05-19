package com.epam.esm.service.validator;

import com.epam.esm.entity.User;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceValidationException;
import com.epam.esm.exception.constant.ErrorTextMessageConstants;
import com.epam.esm.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    private final UserRepository repository;

    @Autowired
    public UserValidator(UserRepository repository) {
        this.repository = repository;
    }

    private void checkLoginUnique(String login, List<InvalidDataMessage> invalidDataMessageList) {
      /*  if (repository.findByLogin(login) != null) {
            invalidDataMessageList.add(
                new InvalidDataMessage(ErrorTextMessageConstants.USER_LOGIN_FIELD_IS_EXIST));
        }*/
    }

    private void checkEmailUnique(String email, List<InvalidDataMessage> invalidDataMessageList) {
       /* if (repository.findByEmail(email) != null) {
            invalidDataMessageList.add(
                new InvalidDataMessage(ErrorTextMessageConstants.USER_EMAIL_FIELD_IS_EXIST));
        }*/
    }

    public void isCorrectUser(User user) {
        List<InvalidDataMessage> invalidDataMessageList = new ArrayList<>();

        checkEmailUnique(user.getEmail(), invalidDataMessageList);
        checkLoginUnique(user.getLogin(), invalidDataMessageList);

        if (!invalidDataMessageList.isEmpty()) {
            throw new ServiceValidationException(invalidDataMessageList);
        }
    }

    public void checkId(Long id) {
        if (id <= 0) {
            throw new ServiceValidationException(
                new InvalidDataMessage(ErrorTextMessageConstants.TAG_ID));
        }
    }
}
