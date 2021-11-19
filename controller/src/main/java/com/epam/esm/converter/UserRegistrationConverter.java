package com.epam.esm.converter;

import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.entity.User;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserRegistrationConverter implements DtoConverter<RegistrationUserDto, User> {

    @Override
    public List<RegistrationUserDto> convertToDto(List<User> users) {
        return users
            .stream()
            .map(RegistrationUserDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public List<User> convertFromDto(List<RegistrationUserDto> dto) {
        return null;
    }

    @Override
    public User convertFromDto(RegistrationUserDto var) {
        return null;
    }

    @Override
    public RegistrationUserDto convertToDto(User var) {
        return null;
    }
}
