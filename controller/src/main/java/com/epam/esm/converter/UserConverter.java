package com.epam.esm.converter;

import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class UserConverter implements DtoConverter<UserDto, User> {

    @Override
    public List<UserDto> convertToDto(List<User> var) {
        return null;
    }

    @Override
    public List<User> convertFromDto(List<UserDto> dto) {
        return null;
    }

    @Override
    public User convertFromDto(UserDto var) {
        return null;
    }

    @Override
    public UserDto convertToDto(User var) {
        return null;
    }
}
