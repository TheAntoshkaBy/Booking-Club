package com.epam.esm.converter;

import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter implements DtoConverter<RegistrationUserDto, User> {

    @Override
    public List<RegistrationUserDto> convert(List<User> users) {
        return users
            .stream()
            .map(RegistrationUserDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public User convert(RegistrationUserDto user) {
        return new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(),
            user.getEmail(), user.getRoles(), null, null);
    }
}
