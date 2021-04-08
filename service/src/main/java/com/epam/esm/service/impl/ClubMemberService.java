package com.epam.esm.service.impl;

import com.epam.esm.entity.Role;
import com.epam.esm.entity.User;
import com.epam.esm.pojo.UserPOJO;
import com.epam.esm.repository.ClubMemberRepository;
import com.epam.esm.repository.MemberRoleRepository;
import com.epam.esm.service.UserService;
import com.epam.esm.service.support.PojoConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClubMemberService implements UserService {

    private final ClubMemberRepository clubMemberRepository;
    private final MemberRoleRepository roleRepository;
    private final PojoConverter<UserPOJO, User> converter;

    @Autowired
    public ClubMemberService(ClubMemberRepository clubMemberRepository, MemberRoleRepository roleRepository,
                             PojoConverter<UserPOJO, User> converter) {
        this.clubMemberRepository = clubMemberRepository;
        this.roleRepository = roleRepository;
        this.converter = converter;
    }

    @Override
    public List<UserPOJO> findAll(int page, int size) {
        return null;
    }

    @Override
    public UserPOJO find(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public UserPOJO create(UserPOJO user) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String actualPassword = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(actualPassword));
        User changeUser = clubMemberRepository.save(converter.convert(user));
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1L).get());
        changeUser.setRoles(roles);
        return new UserPOJO(clubMemberRepository.save(changeUser));
    }

    @Override
    public UserPOJO findByLogin(String login) {
        return new UserPOJO(clubMemberRepository.findByLogin(login));
    }

    @Override
    public int getUsersCount() {
        return 0;
    }
}
