package com.epam.esm.repository;

import com.epam.esm.entity.Book;
import com.epam.esm.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    @Override
    <S extends User> S save(S s);

    List<User> findByIdBetween(Long offset, Long lastBook);

}

