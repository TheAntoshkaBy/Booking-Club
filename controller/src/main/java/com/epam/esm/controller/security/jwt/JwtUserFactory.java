package com.epam.esm.controller.security.jwt;

import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserDTO user) {
        return new JwtUser(user.getId(), user.getLogin(), user.getName(), user.getSurname(),
                           user.getEmail(), user.getPassword(),
                           mapToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
            return userRoles.stream()
            .map(role ->
                new SimpleGrantedAuthority(role.getName())
            ).collect(Collectors.toList());
    }
}
