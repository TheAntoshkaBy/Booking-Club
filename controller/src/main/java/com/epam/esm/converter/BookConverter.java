package com.epam.esm.converter;

import com.epam.esm.dto.BookDto;
import com.epam.esm.entity.Book;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements DtoConverter<BookDto, Book> {

    @Override
    public List<BookDto> convert(List<Book> books) {
        return books.stream().map(BookDto::new).collect(Collectors.toList());
    }

    @Override
    public Book convert(BookDto bookDTO) {
        return new Book(bookDTO.getName(), bookDTO.getDescription(), bookDTO.getAuthor(), bookDTO.getCount(),
            bookDTO.getCreationDate());
    }
}
