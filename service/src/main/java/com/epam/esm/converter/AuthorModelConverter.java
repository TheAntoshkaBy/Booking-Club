package com.epam.esm.converter;

import com.epam.esm.entity.Author;
import com.epam.esm.model.AuthorModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class AuthorModelConverter implements ModelConverter<AuthorModel, Author> {
    @Override
    public List<AuthorModel> convertToModel(List<Author> authors) {
        return authors.stream()
                .map(this::convertToModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Author> convertFromModel(List<AuthorModel> models) {
        return models.stream()
                .map(this::convertFromModel)
                .collect(Collectors.toList());
    }

    @Override
    public Author convertFromModel(AuthorModel model) {
        return new Author(
                model.getId(),
                model.getName(),
                model.getSurname(),
                model.getBiography(),
                model.getOwnBooks()
        );
    }

    @Override
    public AuthorModel convertToModel(Author author) {
        return new AuthorModel(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getBiography(),
                author.getOwnBooks()
        );
    }
}
