package com.epam.esm.service;

import com.epam.esm.entity.User;
import java.util.List;

public interface UserService {

    /**
     * This method finds all users from database using DAO
     **/
    List<User> findAll(int page, int size);

    /**
     * This method finds concrete User By Id using DAO
     *
     * @param id Tag Id
     * @return Tag
     **/
    User findById(long id);

    /**
     * This method delete user by transmitted user id
     *
     * @param id user
     **/
    void delete(long id);

    /**
     * This method add new User
     *
     * @param tag User object
     **/
    User create(User user);

    User findByLogin(String login);

    int getUsersCount();
}
