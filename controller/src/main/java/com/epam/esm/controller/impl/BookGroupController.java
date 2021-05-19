package com.epam.esm.controller.impl;

import com.epam.esm.controller.BookingClubController;
import com.epam.esm.dto.BookGroupDto;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/groups")
public class BookGroupController implements BookingClubController<BookGroupDto> {

    @Override
    public ResponseEntity<?> create(@Valid BookGroupDto bookGroupDto) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<BookGroupDto>> findById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel<BookGroupDto>> update(@Valid BookGroupDto bookGroupDto, int id) {
        return null;
    }
}
