package com.epam.esm.controller.impl;

import com.epam.esm.controller.support.ControllerParamNames;
import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.book.BookDto;
import com.epam.esm.dto.book.BookList;
import com.epam.esm.dto.book.UpdateBookDto;
import com.epam.esm.model.BookModel;
import com.epam.esm.model.UpdateBookModel;
import com.epam.esm.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;
    private final DtoConverter<BookDto, BookModel> converter;
    private final DtoConverter<UpdateBookDto, UpdateBookModel> updateConverter;

    @Autowired
    public BookController(BookService service,
                          @Qualifier("bookConverter")
                          DtoConverter<BookDto, BookModel> converter,
                          @Qualifier("updateBookConverter")
                          DtoConverter<UpdateBookDto, UpdateBookModel> updateConverter) {
        this.service = service;
        this.converter = converter;
        this.updateConverter = updateConverter;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookList> findAll(
            @RequestParam(value = ControllerParamNames.PAGE_PARAM_NAME) final int page,
            @RequestParam(value = ControllerParamNames.SIZE_PARAM_NAME) final int size) {

        List<BookDto> book = converter.convertToDto(service.findAllBooksWithPagination(page, size));
        int count = service.getAllBooksCount();

        return new ResponseEntity<>(new BookList(book, count), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid BookDto bookDto) {

        BookModel book = converter.convertFromDto(bookDto);
        book = service.create(book);
        bookDto = converter.convertToDto(book);

        return ResponseEntity
                .created(linkTo(methodOn(BookController.class).findById(bookDto.getId())).toUri())
                .body(bookDto.getModel());
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BookDto>> findById(@PathVariable long id) {
        BookDto bookDTO = converter.convertToDto(service.findById(id));
        return new ResponseEntity<>(bookDTO.getModel(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<BookDto>> update(@RequestBody @Valid UpdateBookDto bookDto, @PathVariable long id) {
        bookDto.setId(id);
        UpdateBookModel bookModel = updateConverter.convertFromDto(bookDto);
        BookDto book = converter.convertToDto(service.update(bookModel));
        return new ResponseEntity<>(book.getModel(), HttpStatus.OK);
    }
}
