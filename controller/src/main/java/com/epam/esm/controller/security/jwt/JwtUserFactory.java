package com.epam.esm.controller.security.jwt;

import com.epam.esm.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserDto user) {
        return new JwtUser(user.getId(), user.getLogin(), user.getName(), user.getSurname(),
                           user.getEmail(), user.getPassword(),null
        );
    }
}
