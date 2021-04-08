package com.epam.esm.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.UserController;
import com.epam.esm.entity.Role;
import com.epam.esm.pojo.UserPOJO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.EntityModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @ToString.Exclude
    @Null(message ="{validation.user.id}")
    private Long id;

    @NotNull(message = "{validation.user.name}")
    @Size(min = 2, max = 70, message = "{validation.user.name.not.null}")
    private String name;

    @NotNull(message = "{validation.user.surname}")
    @Size(min = 3, max = 170, message = "{validation.user.surname}")
    private String surname;

    @NotNull(message = "{validation.user.login}")
    @Size(min = 5, max = 30, message = "{validation.user.login.not.null}")
    private String login;

    @NotNull(message = "{validation.user.password.not.null}")
    @Size(min = 4, max = 30, message =  "{validation.user.password}")
    @JsonIgnore
    private String password;

    @Email(message = "{validation.user.email}")
    @NotNull(message = "{validation.user.email.not.null}")
    private String email;

    @Null(message = "{validation.user.roles.null}")
    private List<Role> roles;

    @JsonIgnore
    private EntityModel<UserDTO> model;

    public UserDTO(UserPOJO user) {
        this.id = user.getUser_id();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }

    public EntityModel<UserDTO> getModel(int page, int size) {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";

        model = EntityModel.of(this,
            linkTo(methodOn(UserController.class)
                .findUserById(id)).withSelfRel().withType(methodTypeGET),
            linkTo(methodOn(UserController.class)
                .delete(id)).withRel(deleteRelName).withType(methodTypeDELETE)
        );
        return model;
    }

    public EntityModel<UserDTO> getModel() {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";
        int defaultPage = 1;
        int defaultSize = 5;

        model = EntityModel.of(this,
            linkTo(methodOn(UserController.class)
                .findUserById(id)).withSelfRel().withType(methodTypeGET),
            linkTo(methodOn(UserController.class)
                .delete(id)).withRel(deleteRelName).withType(methodTypeDELETE));
        return model;
    }
}
