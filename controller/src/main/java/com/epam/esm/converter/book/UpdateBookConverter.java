package com.epam.esm.converter.book;

import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.book.UpdateBookDto;
import com.epam.esm.model.UpdateBookModel;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.utils.DateFormatter.*;

@Component
public class UpdateBookConverter implements DtoConverter<UpdateBookDto, UpdateBookModel> {
    @Override
    public List<UpdateBookDto> convertToDto(final List<UpdateBookModel> books) {
        return books.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UpdateBookModel> convertFromDto(List<UpdateBookDto> dto) {
        return null;
    }

    @Override
    public UpdateBookModel convertFromDto(final UpdateBookDto bookDTO) {
        final Date date = bookDTO.getYearTheBookWasPrinted() != null
                ? parseYear(bookDTO.getYearTheBookWasPrinted())
                : null;

        return new UpdateBookModel(
                bookDTO.getId(),
                bookDTO.getName(),
                bookDTO.getCount(),
                bookDTO.getDescription(),
                date
        );
    }

    @Override
    public UpdateBookDto convertToDto(final UpdateBookModel book) {
        final String year = book.getYearTheBookWasPrinted() != null
                ? formatYear(book.getYearTheBookWasPrinted())
                : null;

        return new UpdateBookDto(
                book.getId(),
                book.getName(),
                book.getDescription(),
                book.getCount(),
                year
        );
    }
}
