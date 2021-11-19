package com.epam.esm.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.impl.UserController;
import com.epam.esm.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.EntityModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private Long id;
    private String name;
    private String surname;
    private String login;
    private String email;

    @JsonIgnore
    private String password;
    @JsonIgnore
    private EntityModel<UserDto> model;

    public EntityModel<UserDto> getModel(int page, int size) {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";

        /*model = EntityModel.of(this,
            linkTo(methodOn(UserController.class)
                .findById(id)).withSelfRel().withType(methodTypeGET),
            linkTo(methodOn(UserController.class)
                .delete(id)).withRel(deleteRelName).withType(methodTypeDELETE)
        );*/
        return model;
    }

    public EntityModel<UserDto> getModel() {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";
        int defaultPage = 1;
        int defaultSize = 5;

       /* model = EntityModel.of(this,
            linkTo(methodOn(UserController.class)
                .findById(id)).withSelfRel().withType(methodTypeGET),
            linkTo(methodOn(UserController.class)
                .delete(id)).withRel(deleteRelName).withType(methodTypeDELETE));*/
        return model;
    }
}
