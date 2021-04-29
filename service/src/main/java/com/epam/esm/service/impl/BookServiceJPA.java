package com.epam.esm.service.impl;

import static org.apache.logging.log4j.util.Strings.isBlank;

import com.epam.esm.entity.Book;
import com.epam.esm.entity.Review;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.repository.BookRepository;
import com.epam.esm.repository.MemberReviewRepository;
import com.epam.esm.service.BookService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BookServiceJPA implements BookService {

    private final BookRepository bookRepository;
    private final MemberReviewRepository reviewRepository;

    @Autowired
    public BookServiceJPA(BookRepository bookRepository, MemberReviewRepository reviewRepository) {
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
    public List<Book> findAll(int page, int size) {
        long offset = page * size;

        return bookRepository.findByIdBetween(offset, offset + size);
    }

    @Override
    public int getBookCount(long id) {
        Book book = findBookInRepository(id);
        return book.getCount();
    }

    @Override
    public Book find(long id) {
        return findBookInRepository(id);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void update(long id, Book book) {
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

        bookRepository.update(book.getName(), book.getDescription(), book.getAuthor());
    }

    @Override
    public Book create(Book book) {
        book.setCreationDate(new Date());
        return bookRepository.save(book);
    }

    private Book findBookInRepository(long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new ServiceBadRequestException(new InvalidDataMessage("Invalid Id!")));
    }
}
