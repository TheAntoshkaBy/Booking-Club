package com.epam.esm.service;

import com.epam.esm.entity.User;
import java.util.List;

public interface UserService {

    /**
     * This method finds all users from database using DAO
     **/
    List<User> findAllUsersWithPagination(int page, int size);

    List<User> findAllUsersWithPagination();

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

    User create(User user);

    User update(long id, User user);

    User findByLogin(String login);

    int getUsersCount();
}
