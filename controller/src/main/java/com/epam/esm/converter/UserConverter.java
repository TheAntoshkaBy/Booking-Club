package com.epam.esm.converter;

import com.epam.esm.dto.RegistrationUserDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.User;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements DtoConverter<UserDTO, User> {

    @Override
    public List<UserDTO> convert(List<User> users) {
        return users
            .stream()
            .map(UserDTO::new)
            .collect(Collectors.toList());
    }

    @Override
    public User convert(UserDTO user) {
        return new User(user.getId(), user.getName(), user.getSurname(), user.getLogin(), user.getPassword(),
            user.getEmail(), user.getRoles(), null, null);
    }
}
