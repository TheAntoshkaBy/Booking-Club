package com.epam.esm.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.impl.UserController;
import com.epam.esm.controller.security.jwt.JwtTokenProvider;
import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.AuthenticationRequestDto;
import com.epam.esm.dto.RegistrationUserDto;
import com.epam.esm.dto.UserDto;
import com.epam.esm.entity.User;
import com.epam.esm.exception.ControllerBadRequestException;
import com.epam.esm.exception.InvalidControllerOutputMessage;
import com.epam.esm.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping()
public class AuthentificationController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final DtoConverter<RegistrationUserDto, User> registrationConverter;

    public AuthentificationController(UserService service, AuthenticationManager authenticationManager,
                                      JwtTokenProvider jwtTokenProvider,
                                      DtoConverter<RegistrationUserDto, User> registrationConverter) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.registrationConverter = registrationConverter;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto,
                                                     HttpServletRequest request, HttpServletResponse response) {
        String invalid = "Invalid username or password";
        String parameterUsername = "username";
        String parameterIsLogged = "isLogged";
        String parameterToken = "token";

        try {
            String username = requestDto.getLogin();
            authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserDto user = new UserDto(service.findByLogin(username));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> responseMap = new HashMap<>();
            responseMap.put(parameterUsername, username);
            responseMap.put(parameterToken, token);
            responseMap.put(parameterIsLogged, true);
            /*String path = linkTo(methodOn(UserController.class)
                .findUserById(user.getId(),request)).toString();
            response.setHeader("Location", path);*/

            return ResponseEntity.ok(responseMap);
        } catch (AuthenticationException e) {
            Map<Object, Object> responseMap = new HashMap<>();
            responseMap.put(parameterUsername, "");
            responseMap.put(parameterToken, "");
            responseMap.put(parameterIsLogged, false);
            return ResponseEntity.ok(responseMap);
        }
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/registration")
    public ResponseEntity<EntityModel<UserDto>> registration(@Valid @RequestBody RegistrationUserDto userDTO,
                                                             HttpServletRequest request) {
        String invalid = "Invalid data";

        try {
            User user = service.create(registrationConverter.convert(userDTO));
            UserDto createdUser = new UserDto(user);
            return ResponseEntity
                .created(linkTo(methodOn(UserController.class).findById(createdUser.getId())).toUri())
                .body(createdUser.getModel());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(invalid);
        }
    }
}
