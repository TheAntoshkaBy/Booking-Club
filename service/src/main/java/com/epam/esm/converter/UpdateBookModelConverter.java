package com.epam.esm.converter;

import com.epam.esm.entity.Book;
import com.epam.esm.model.UpdateBookModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateBookModelConverter implements ModelConverter<UpdateBookModel, Book> {

    @Override
    public List<UpdateBookModel> convertToModel(List<Book> var) {
        return null;
    }

    @Override
    public List<Book> convertFromModel(List<UpdateBookModel> models) {
        return null;
    }

    @Override
    public Book convertFromModel(UpdateBookModel model) {
        return new Book(
                model.getId(),
                model.getName(),
                model.getDescription(),
                null,
                model.getYearTheBookWasPrinted(),
                null
        );
    }

    @Override
    public UpdateBookModel convertToModel(Book book) {
        return new UpdateBookModel(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getAddedDate()
        );
    }
}
