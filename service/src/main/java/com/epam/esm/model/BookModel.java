package com.epam.esm.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class BookModel extends UpdateBookModel {

    protected Date addedDate;
    private List<AuthorModel> authors;

    public BookModel(Long id, String name, String description, Date creationDate, List<AuthorModel> authors) {
        super(id, name, description, creationDate);
        this.authors = authors;
    }

    public BookModel(Long id,
                     String name,
                     String description,
                     Date addedDate,
                     List<AuthorModel> authors,
                     Date yearTheBookWasPrinted) {
        super(id, name, description, yearTheBookWasPrinted);
        this.authors = authors;
        this.addedDate = addedDate;
    }
}
