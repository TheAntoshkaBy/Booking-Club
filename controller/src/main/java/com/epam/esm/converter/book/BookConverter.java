package com.epam.esm.converter.book;

import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.AuthorDto;
import com.epam.esm.dto.book.BookDto;
import com.epam.esm.model.AuthorModel;
import com.epam.esm.model.BookModel;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import static com.epam.esm.utils.DateFormatter.*;

@Component
public class BookConverter implements DtoConverter<BookDto, BookModel> {

    private final DtoConverter<AuthorDto, AuthorModel> converter;

    @Autowired
    public BookConverter(@Qualifier(value = "authorDtoConverter")
                                     DtoConverter<AuthorDto, AuthorModel> converter) {
        this.converter = converter;
    }

    @Override
    public List<BookDto> convertToDto(final List<BookModel> books) {
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookModel> convertFromDto(List<BookDto> dto) {
        return null;
    }

    @Override
    public BookModel convertFromDto(final BookDto bookDTO) {
        final Date date = bookDTO.getCreationDate() != null
                ? parseDate(bookDTO.getCreationDate())
                : null;
        final Date year = bookDTO.getYearTheBookWasPrinted() != null
                ? parseYear(bookDTO.getYearTheBookWasPrinted())
                : null;
        final List<AuthorModel> authors = bookDTO.getAuthors() != null
                ? converter.convertFromDto(bookDTO.getAuthors())
                : null;

        return new BookModel(
                bookDTO.getId(),
                bookDTO.getName(),
                bookDTO.getDescription(),
                date,
                authors,
                year
        );
    }

    @Override
    public BookDto convertToDto(final BookModel book) {
        final List<AuthorDto> authors = book.getAuthors() != null
                ? converter.convertToDto(book.getAuthors())
                : null;
        final String creationDate = book.getAddedDate() != null
                ? formatDate(book.getAddedDate())
                : null;
        final String year = book.getYearTheBookWasPrinted() != null
                ? formatYear(book.getYearTheBookWasPrinted())
                : null;

        return new BookDto(
                book.getId(),
                book.getName(),
                book.getDescription(),
                creationDate,
                book.getCount(),
                authors,
                year
        );
    }

}
