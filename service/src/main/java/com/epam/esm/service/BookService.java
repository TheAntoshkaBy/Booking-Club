package com.epam.esm.service;

import com.epam.esm.model.BookModel;
import com.epam.esm.model.UpdateBookModel;

import java.util.List;

public interface BookService {

    List<BookModel> findAllBooksWithPagination(int page, int size);

    /**
     * This method finds concrete Certificate By Id
     *
     * @param id Certificate Id
     * @return Certificate
     **/
    BookModel findById(long id);

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
    BookModel create(BookModel book);

    BookModel update(UpdateBookModel book);

    int getAllBooksCount();
}
