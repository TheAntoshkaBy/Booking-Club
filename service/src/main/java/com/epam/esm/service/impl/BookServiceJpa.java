package com.epam.esm.service.impl;

import com.epam.esm.converter.ModelConverter;
import com.epam.esm.entity.Book;
import com.epam.esm.exception.InvalidDataMessage;
import com.epam.esm.exception.ServiceBadRequestException;
import com.epam.esm.model.BookModel;
import com.epam.esm.model.UpdateBookModel;
import com.epam.esm.repository.AuthorRepository;
import com.epam.esm.repository.BookRepository;
import com.epam.esm.service.BookService;
import com.epam.esm.service.support.OffsetBasedPageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BookServiceJpa implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelConverter<BookModel, Book> converter;
    private final ModelConverter<UpdateBookModel, Book> updateConverter;


    @Autowired
    public BookServiceJpa(BookRepository bookRepository,
                          AuthorRepository authorRepository,
                          @Qualifier(value = "bookModelConverter")
                                      ModelConverter<BookModel, Book> converter,
                          @Qualifier(value = "updateBookModelConverter")
                                      ModelConverter<UpdateBookModel, Book> updateConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.converter = converter;
        this.updateConverter = updateConverter;
    }

    @Override
    public List<BookModel> findAllBooksWithPagination(int page, int size) {
        final int limit = page * size - size;
        final Pageable pageable = new OffsetBasedPageRequest(size, limit);
        return converter.convertToModel(new ArrayList<>(bookRepository.findAll(pageable).getContent()));
    }

    @Override
    public int getBookCount(long id) {
        BookModel book = findBookInRepository(id);
        return book.getCount();
    }

    @Override
    public BookModel findById(long id) {
        return findBookInRepository(id);
    }

    @Override
    public void delete(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookModel update(UpdateBookModel book) {
        Book originalBook = converter.convertFromModel(this.findById(book.getId()));
        Book mutableBook = updateConverter.convertFromModel(book);
        mutableBook.setAddedDate(originalBook.getAddedDate());
        mutableBook.setAuthors(originalBook.getAuthors());
        mutableBook.setYearTheBookWasPrinted(mutableBook.getYearTheBookWasPrinted());
        bookRepository.save(mutableBook);
        return findBookInRepository(mutableBook.getId());
    }

    @Override
    public int getAllBooksCount() {
        return bookRepository.findAll().size();
    }

    @Override
    public BookModel create(BookModel bookModel) {
        bookModel.setAddedDate(new Date());
        Book book = converter.convertFromModel(bookModel);
        book = bookRepository.save(book);
        bookModel = converter.convertToModel(book);
        bookModel.setCount(bookRepository.countAllByNameAndAuthorsIn(book.getName(), book.getAuthors()));
        return bookModel;
    }

    private BookModel findBookInRepository(long id) {
        final Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ServiceBadRequestException(new InvalidDataMessage("This ID is does not exist!")));
        BookModel bookModel = converter.convertToModel(book);
        bookModel.setCount(bookRepository.countAllByNameAndAuthorsIn(book.getName(), book.getAuthors()));
        return bookModel;
    }
}
