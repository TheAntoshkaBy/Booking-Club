package com.epam.esm.service.ext;

import com.epam.esm.entity.Book;
import com.epam.esm.entity.Review;
import java.util.List;
import java.util.Map;

public interface BookService extends BookingClubService<Book> {

    /**
     * This method find count of certificates.
     **/
    int getBooksCount(Map<String, String> request, List<Review> reviews);

    List<Book> findAll(int page, int size);

    /**
     * This method finds concrete Certificate By Id
     *
     * @param id Certificate Id
     * @return Certificate
     **/
    Book findById(long id);

    /**
     * This method delete concrete Certificate by transmitted id
     *
     * @param id certificate id which will be delete
     **/
    void delete(long id);

    int getBookCount(long id);

    /**
     * This method add new Certificate using DAO
     *
     * @param book Certificate object
     **/
    Book create(Book book);

    Book update(Book book, long id);

    int getAllBooksCount();
}
