package com.epam.esm.converter;

import com.epam.esm.dto.BookDTO;
import com.epam.esm.entity.Book;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class BookConverter implements DtoConverter<BookDTO, Book> {

    @Override
    public List<BookDTO> convert(List<Book> books) {
        return books.stream().map(BookDTO::new).collect(Collectors.toList());
    }

    @Override
    public Book convert(BookDTO bookDTO) {
        return new Book(bookDTO.getName(), bookDTO.getDescription(), bookDTO.getAuthor(), bookDTO.getCount(),
            bookDTO.getCreationDate());
    }
}
