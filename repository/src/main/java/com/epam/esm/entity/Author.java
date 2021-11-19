package com.epam.esm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "author")
public class Author {
    @Id
    private Long id;
    private String name;
    private String surname;

    @Column(length = 40000)
    private String biography;

    @ManyToMany(mappedBy = "authors")
    private List<Book> ownBooks;
}
