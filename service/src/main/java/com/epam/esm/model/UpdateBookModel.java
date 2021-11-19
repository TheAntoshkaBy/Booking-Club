package com.epam.esm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookModel {
    protected Long id;
    protected String name;
    protected int count;
    protected String description;
    protected Date yearTheBookWasPrinted;


    public UpdateBookModel(Long id, String name, String description, Date yearTheBookWasPrinted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.yearTheBookWasPrinted = yearTheBookWasPrinted;
    }
}
