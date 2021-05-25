package com.epam.esm.service.ext.impl;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.epam.esm.entity.Book;
import com.epam.esm.entity.Review;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.repository.BookRepository;
import com.epam.esm.repository.ReviewRepository;
import com.epam.esm.service.ext.BookService;
import com.epam.esm.service.support.OffsetBasedPageRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookServiceJpa implements BookService {

    private final BookRepository bookRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public BookServiceJpa(BookRepository bookRepository, ReviewRepository reviewRepository) {
        this.bookRepository = bookRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public int getBooksCount(Map<String, String> request, List<Review> reviews) {
        return bookRepository.getBookCount();
    }

    /**
     * @param params
     * @return Book list
     */
    @Override
    public List<Book> findAllBooksWithPagination(int page, int size) {
        int limit = page*size-size;
            Pageable pageable = new OffsetBasedPageRequest(size, limit);
        Page<Book> page1 = bookRepository.findAll(pageable);
        return page1.getContent();
    }

    @Override
    public int getBookCount(long id) {
        Book book = findBookInRepository(id);
        return book.getCount();
    }

    @Override
    public Book findById(long id) {
        return findBookInRepository(id);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book update(Book book, long id) {
        Book mutableBook = findBookInRepository(id);

        if (isBlank(book.getAuthor())) {
            book.setAuthor(mutableBook.getAuthor());
        }
        if (isBlank(book.getName())) {
            book.setName(mutableBook.getName());
        }
        if (isBlank(book.getDescription())) {
            book.setDescription(mutableBook.getDescription());
        }

        bookRepository.update(id, book.getName(), book.getDescription(), book.getAuthor());
        return this.findById(id);
    }

    @Override
    public int getAllBooksCount() {
        return bookRepository.findAll().size();
    }

    @Override
    public Book create(Book book) {
        book.setCreationDate(new Date());
        return bookRepository.save(book);
    }

    private Book findBookInRepository(long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ServiceBadRequestException(new InvalidDataMessage("This ID is does not exist!")));
    }
}
