package com.epam.esm.controller.impl;

import com.epam.esm.controller.BookingClubController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/orders")
public class OrderController implements BookingClubController {

    @Override
    public ResponseEntity<?> create(Object o) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel> findById(long id) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<EntityModel> update(Object o, int id) {
        return null;
    }
}
