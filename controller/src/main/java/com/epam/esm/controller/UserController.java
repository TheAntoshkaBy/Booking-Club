package com.epam.esm.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.security.jwt.JwtTokenProvider;
import com.epam.esm.converter.DtoConverter;
import com.epam.esm.dto.AuthenticationRequestDto;
import com.epam.esm.dto.RegistrationUserDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.exception.ControllerBadRequestException;
import com.epam.esm.exception.InvalidControllerOutputMessage;
import com.epam.esm.pojo.UserPOJO;
import com.epam.esm.service.UserService;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/users")
public class UserController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final DtoConverter<RegistrationUserDTO, UserPOJO> registrationConverter;

    public UserController(UserService service, AuthenticationManager authenticationManager,
                          JwtTokenProvider jwtTokenProvider,
                          DtoConverter<RegistrationUserDTO, UserPOJO> registrationConverter) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.registrationConverter = registrationConverter;
    }

    /* @PatchMapping(path = "{id}/orders")
        public ResponseEntity<EntityModel<CertificateOrderDTO>> addOrder(
                                                    @PathVariable long id, HttpServletRequest request,
                                                    @RequestBody CertificateOrderDTO orderDTO) {
            ControllerUtils.checkUserRulesById(request, id);
            UserDTO createdUserDTO = new UserDTO(service.find(id));
            UserPOJO createdUserPOJO = converter.convert(createdUserDTO);

            CertificateOrderDTO resultOrder = new CertificateOrderDTO(
                orderService.create(orderConverter.convert(orderDTO),
                    createdUserPOJO));

            return ResponseEntity.created(linkTo(methodOn(OrderController.class)
                .findOrderById(resultOrder.getId()))
                .toUri()).body(resultOrder.getModel());
        }

        @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<UserList> findAll(
            @RequestParam(value = ControllerParamNames.PAGE_PARAM_NAME,
                defaultValue = ControllerParamNames.DEFAULT_PAGE_STRING) int page,
            @RequestParam(value = ControllerParamNames.SIZE_PARAM_NAME,
                defaultValue = ControllerParamNames.DEFAULT_SIZE_STRING) int size) {
            List<UserPOJO> users = service.findAll(page, size);
            int usersCount = service.getUsersCount();

            return new ResponseEntity<>(
                new UserListBuilder(users, converter)
                    .resultCount(usersCount)
                    .page(page).size(size)
                    .build(), HttpStatus.OK);
        }*/

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDTO>> findUserById(@PathVariable long id, HttpServletRequest request) {
        int startPage = 1;
        int startSize = 5;

        //ControllerUtils.checkUserRulesById(request, id);
        UserDTO searchedUser = new UserDTO(service.find(id));

        return new ResponseEntity<>(searchedUser.getModel(startPage, startSize), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDTO>> findUserByLogin(@RequestParam(value="login") String login, HttpServletRequest request) {
        int startPage = 1;
        int startSize = 5;

        //ControllerUtils.checkUserRulesById(request, id);
        UserDTO searchedUser = new UserDTO(service.findByLogin(login));

        return new ResponseEntity<>(searchedUser.getModel(startPage, startSize), HttpStatus.OK);
    }

    /*  @GetMapping(path = "/{id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<OrderList> findOrders(@PathVariable long id,
          @RequestParam(value = ControllerParamNames.PAGE_PARAM_NAME,
              defaultValue = ControllerParamNames.DEFAULT_PAGE_STRING) int page,
          @RequestParam(value = ControllerParamNames.SIZE_PARAM_NAME,
              defaultValue = ControllerParamNames.DEFAULT_SIZE_STRING) int size,
                                                                       HttpServletRequest request) {
          ControllerUtils.checkUserRulesById(request, id);
          List<CertificateOrderPOJO> ordersByUser = orderService.findAllByOwner(id, page, size);
          int ordersCount = orderService.ordersCountByOwner(id);

          return new ResponseEntity<>(
              new OrderListBuilder(ordersByUser, orderConverter)
                  .resultCount(ordersCount)
                  .page(page)
                  .size(size)
                  .build(), HttpStatus.OK);
      }


      @DeleteMapping(path = "/{userId}/orders/{id}")
      public ResponseEntity<Void> deleteOrder(@PathVariable Long id, @PathVariable Long userId,
                                              HttpServletRequest request) {
          ControllerUtils.checkIsCurrentUserHaveRulesForEditThisOrder(userId, id);
          ControllerUtils.checkUserRulesById(request, userId);

          orderService.delete(id);

          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      @PatchMapping(path = "{userId}/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
      public ResponseEntity<EntityModel<CertificateOrderDTO>> addCertificates(
                                                              @PathVariable long userId,
                                                              @PathVariable long id,
                                                              @RequestBody AddedCertificatesListDTO
                                                                  certificatesId,
                                                              HttpServletRequest request) {
          ControllerUtils.checkIsCurrentUserHaveRulesForEditThisOrder(userId, id);
          ControllerUtils.checkUserRulesById(request, userId);


          CertificateOrderDTO certificateOrderDTO =
              new CertificateOrderDTO(orderService.addCertificates(id, certificatesId
                                                                            .getAddedCertificates()));
          return new ResponseEntity<>(certificateOrderDTO.getModel(), HttpStatus.OK);
      }
  */
    @PostMapping(path = "/login")
    public ResponseEntity<Map<Object, Object>> login(@RequestBody AuthenticationRequestDto requestDto,
                                                     HttpServletRequest request, HttpServletResponse response) {
        String invalid = "Invalid username or password";
        String parameterUsername = "username";
        String parameterToken = "token";

        try {
            String username = requestDto.getLogin();
            authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            UserDTO user = new UserDTO(service.findByLogin(username));

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> responseMap = new HashMap<>();
            responseMap.put(parameterUsername, username);
            responseMap.put(parameterToken, token);
            /*String path = linkTo(methodOn(UserController.class)
                .findUserById(user.getId(),request)).toString();
            response.setHeader("Location", path);*/

            return ResponseEntity.ok(responseMap);
        } catch (AuthenticationException e) {
            throw new ControllerBadRequestException(new InvalidControllerOutputMessage(invalid));
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<UserDTO>> registration(
        @Valid @RequestBody RegistrationUserDTO userDTO, HttpServletRequest request) {
        String invalid = "Invalid data";

        try {
            UserPOJO userPOJO = service
                .create(registrationConverter.convert(userDTO));
            UserDTO createdUser = new UserDTO(userPOJO);
            return ResponseEntity.created(linkTo(methodOn(UserController.class)
                                 .findUserById(createdUser.getId(),request))
                                 .toUri()).body(createdUser.getModel());
        } catch (AuthenticationException e) {
            throw new BadCredentialsException(invalid);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public ResponseEntity<EntityModel<UserDTO>> findUserById(long id) {
        int startPage = 1;
        int startSize = 5;

        UserDTO searchedUserDTO = new UserDTO(service.find(id));
        return new ResponseEntity<>(searchedUserDTO.getModel(startPage, startSize), HttpStatus.OK);
    }
}
