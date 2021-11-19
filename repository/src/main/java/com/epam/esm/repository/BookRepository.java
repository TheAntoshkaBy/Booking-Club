package com.epam.esm.repository;

import com.epam.esm.entity.Author;
import com.epam.esm.entity.Book;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update book b set b.name = :name, b.description = :description, b.authors = :authors where b.id = :id")
    void update(@Param("id") long id, @Param("name") String name, @Param("description") String description,
                                         @Param("authors") List<Author> authors);

    int countAllByNameAndAuthorsIn(String name, List<Author> authors);
}
