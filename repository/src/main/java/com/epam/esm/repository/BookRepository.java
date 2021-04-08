package com.epam.esm.repository;

import com.epam.esm.entity.Book;
import com.epam.esm.entity.Review;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    @Override
    List<Book> findAll(Sort sort);

    @Override
    List<Book> findAllById(Iterable<Long> iterable);
}
