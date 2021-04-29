package com.epam.esm.service.support.impl;

import com.epam.esm.entity.User;
import com.epam.esm.service.support.PojoConverter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class UserPojoConverter implements PojoConverter<UserPOJO, User> {

    @Override
    public List<UserPOJO> convert(List<User> users) {
        return users
            .stream()
            .map(UserPOJO::new)
            .collect(Collectors.toList());
    }

    @Override
    public User convert(UserPOJO userPOJO) {
        if (userPOJO.getUser_id() == null) {
            return new User(userPOJO.getName(), userPOJO.getSurname(), userPOJO.getLogin(),
                            userPOJO.getPassword(), userPOJO.getEmail()
            );
        } else {
            return new User(userPOJO.getUser_id(), userPOJO.getName(), userPOJO.getSurname(),
                            userPOJO.getLogin(), userPOJO.getPassword(),
                            userPOJO.getEmail(), userPOJO.getRoles(), userPOJO.getBookGroups()
            );
        }
    }
}
