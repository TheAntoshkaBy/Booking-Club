package com.epam.esm.model;

import com.epam.esm.entity.Book;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorModel {
    private Long id;
    private String name;
    private String surname;
    private String biography;
    private List<Book> ownBooks;
}
