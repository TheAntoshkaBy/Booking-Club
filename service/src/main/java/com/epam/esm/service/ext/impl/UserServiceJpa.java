package com.epam.esm.service.ext.impl;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.repository.RoleRepository;
import com.epam.esm.service.UserService;
import com.epam.esm.service.support.OffsetBasedPageRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceJpa implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceJpa(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAllUsersWithPagination(int page, int size) {

        int limit = page*size-size;
        Pageable pageable = new OffsetBasedPageRequest(size, limit);
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public List<User> findAllUsersWithPagination() {
        return userRepository.findAll();
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ServiceBadRequestException(new InvalidDataMessage("Invalid Id!")));
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User create(User user) {

        long userClassicRole = 1L;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String actualPassword = user.getPassword();
        String hashedPassword = bCryptPasswordEncoder.encode(actualPassword);
        user.setPassword(hashedPassword);
        User changeUser = userRepository.save(user);
        ArrayList<Role> roles = new ArrayList<>();
        roleRepository.findById(userClassicRole).ifPresent(roles::add);
        changeUser.setRoles(roles);
        return userRepository.save(changeUser);
    }

    @Override
    public User update(long id, User user) {
        User mutableUser = this.findById(id);

        if (isBlank(user.getName())) {
            user.setName(mutableUser.getName());
        }
        if (isBlank(user.getSurname())) {
            user.setSurname(mutableUser.getSurname());
        }
        if (isBlank(user.getEmail())) {
            user.setEmail(mutableUser.getEmail());
        }
        if (isBlank(user.getLogin())) {
            user.setLogin(mutableUser.getLogin());
        }

        userRepository.update(id, user.getName(), user.getSurname(), user.getEmail(), user.getLogin());
        return this.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public int getUsersCount() {
        return 0;
    }
}
