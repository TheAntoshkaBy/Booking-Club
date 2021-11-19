package com.epam.esm.converter;

import com.epam.esm.entity.Author;
import com.epam.esm.entity.Book;
import com.epam.esm.model.AuthorModel;
import com.epam.esm.model.BookModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookModelConverter implements ModelConverter<BookModel, Book> {

    private final ModelConverter<AuthorModel, Author> authorConverter;

    @Autowired
    public BookModelConverter(ModelConverter<AuthorModel, Author> authorConverter) {
        this.authorConverter = authorConverter;
    }

    @Override
    public List<BookModel> convertToModel(List<Book> books) {
        return books.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Book> convertFromModel(List<BookModel> models) {
        return null;
    }

    @Override
    public Book convertFromModel(BookModel model) {
        List<Author> authors = model.getAuthors() != null
                ?  authorConverter.convertFromModel(model.getAuthors())
                : null;

        return new Book(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getAddedDate(),
                model.getYearTheBookWasPrinted(),
                authors
        );
    }

    @Override
    public BookModel convertToModel(final Book book) {
        return new BookModel(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAddedDate(),
                authorConverter.convertToModel(book.getAuthors()),
                book.getYearTheBookWasPrinted()
        );
    }
}
