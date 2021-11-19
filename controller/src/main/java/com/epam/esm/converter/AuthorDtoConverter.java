package com.epam.esm.converter;

import com.epam.esm.dto.AuthorDto;
import com.epam.esm.model.AuthorModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorDtoConverter implements DtoConverter<AuthorDto, AuthorModel> {
    @Override
    public List<AuthorDto> convertToDto(List<AuthorModel> models) {
        return models.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorModel> convertFromDto(List<AuthorDto> dto) {
        return dto.stream()
                .map(this::convertFromDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorModel convertFromDto(AuthorDto dto) {
        return new AuthorModel(
                dto.getId(),
                dto.getName(),
                dto.getSurname(),
                dto.getBiography(),
                null
        );
    }

    @Override
    public AuthorDto convertToDto(AuthorModel model) {
        return new AuthorDto(
                model.getId(),
                model.getName(),
                model.getSurname(),
                model.getBiography(),
                null
        );
    }
}
