package com.epam.esm.repository;

import com.epam.esm.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMemberRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);

    @Override
    <S extends User> S save(S s);

    List<User> findByIdBetween(Long offset, Long lastBook);

    void deleteById(long id);

    @Modifying(clearAutomatically = true)
    @Query("update bk_user u set u.name = :name, u.surname = :surname, u.email = :email, u.login = :login where u.id = :id")
    void update(@Param("id") long id, @Param("name") String name, @Param("surname") String surname,
                @Param("email") String email, @Param("login") String login);
}

