package com.epam.esm.repository;

import com.epam.esm.entity.Book;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    List<Book> findByIdBetween(Long offset, Long lastBook);

    @Override
    List<Book> findAll(Sort sort);

    @Override
    List<Book> findAllById(Iterable<Long> iterable);

    int getBookById(long id);

    @Modifying(clearAutomatically = true)
    @Query("update book b set b.name = :name, b.description = :description, b.author = :author where b.id = :id")
    void update(@Param("id") long id, @Param("name") String name, @Param("description") String description,
                                         @Param("author") String author);

    @Modifying
    @Query("select b.count from book b where b.id = :id")
    int getBookCount();
}
