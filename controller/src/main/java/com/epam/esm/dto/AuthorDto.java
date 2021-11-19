package com.epam.esm.dto;

import com.epam.esm.dto.book.BookDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;
    private String biography;
    private List<BookDto> ownBooks;
}
