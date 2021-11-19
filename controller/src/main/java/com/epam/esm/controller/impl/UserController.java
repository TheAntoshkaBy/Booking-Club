package com.epam.esm.controller.impl;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.BookingClubController;
import com.epam.esm.controller.support.ControllerParamNames;
import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.UserDto;
import com.epam.esm.dto.UserList;
import com.epam.esm.entity.User;
import com.epam.esm.service.UserService;

import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController/* implements BookingClubController<UserDto> */{
/*
    private final UserService service;
    private final DtoConverter<UserDto, User> converter;

    public UserController(UserService service, DtoConverter<UserDto, User> converter) {
        this.service = service;
        this.converter = converter;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserList> findAll(
        @RequestParam(value = ControllerParamNames.PAGE_PARAM_NAME, defaultValue = ControllerParamNames.DEFAULT_PAGE_STRING) int page,
        @RequestParam(value = ControllerParamNames.SIZE_PARAM_NAME, defaultValue = ControllerParamNames.DEFAULT_SIZE_STRING) int size
    ) {

        List<UserDto> searchedUsers = converter.convertToDto(service.findAllUsersWithPagination(page, size));
        int count = service.findAllUsersWithPagination().size();
        return new ResponseEntity<>(new UserList(searchedUsers, count), HttpStatus.OK);
    }

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDto>> findById(@PathVariable long id) {

        UserDto searchedUser = new UserDto(service.findById(id));

        return new ResponseEntity<>(searchedUser.getModel(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/find",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDto>> findByLogin(@RequestParam(value="login") String login) {

        UserDto searchedUser = new UserDto(service.findByLogin(login));

        return new ResponseEntity<>(searchedUser.getModel(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)//+
    public ResponseEntity<EntityModel<UserDto>> update(@RequestBody @Valid UserDto userDTO, @PathVariable int id) throws ParseException {

        return new ResponseEntity<>(new UserDto(service.update(id, converter.convertFromDto(userDTO))).getModel(), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDto>> create(@Valid @RequestBody UserDto userDTO) throws ParseException {

            User user = service.create(converter.convertFromDto(userDTO));
            UserDto createdUser = new UserDto(user);
            return ResponseEntity
                .created(linkTo(methodOn(UserController.class).findById(createdUser.getId())).toUri())
                .body(createdUser.getModel());
    }*/
}
