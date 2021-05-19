package com.epam.esm.converter;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements DtoConverter<UserDto, User> {

    @Override
    public List<UserDto> convert(List<User> users) {
        return users
            .stream()
            .map(UserDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public User convert(UserDto user) {
        return new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(),
            user.getEmail(), user.getRoles(), null, null);
    }
}
