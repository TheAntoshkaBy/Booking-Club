package com.epam.esm.service.impl;

import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.repository.ClubMemberRepository;
import com.epam.esm.repository.MemberRoleRepository;
import com.epam.esm.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClubMemberService implements UserService {

    private final ClubMemberRepository userRepository;
    private final MemberRoleRepository roleRepository;

    @Autowired
    public ClubMemberService(ClubMemberRepository userRepository, MemberRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<User> findAll(int page, int size) {
        long offset = page * size;

        return userRepository.findByIdBetween(offset, offset + size);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ServiceBadRequestException(new InvalidDataMessage("Invalid Id!")));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User create(User user) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String actualPassword = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(actualPassword));
        User changeUser = userRepository.save(user);
        ArrayList<Role> roles = new ArrayList<>();
        roleRepository.findById(1L).ifPresent(roles::add);
        changeUser.setRoles(roles);
        return userRepository.save(changeUser);
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
