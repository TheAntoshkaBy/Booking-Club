package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookList {

    private List<BookDto> items;
    private int booksCount;

    public BookList(List<BookDto> items, int booksCount) {
        this.items = items;
        this.booksCount = booksCount;
    }
}
