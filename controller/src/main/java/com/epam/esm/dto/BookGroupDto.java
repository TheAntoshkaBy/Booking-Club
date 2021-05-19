package com.epam.esm.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.impl.BookGroupController;
import com.epam.esm.entity.Book;
import com.epam.esm.entity.BookGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import javax.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.EntityModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookGroupDto {

    @ToString.Exclude
    @Null(message = "{validation.book.id}")
    private Long id;

    private String name;

    private String description;

    private List<Book> books;

    @JsonIgnore
    private EntityModel<BookGroupDto> model;

    public BookGroupDto(BookGroup group) {
        this.id = group.getId();
        this.name = group.getName();
        this.description = group.getDescription();
        this.books = getBooks();
    }

    public EntityModel<BookGroupDto> getModel() {
        String deleteRelName = "delete";
        String groupRelName = "groups";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";
        int defaultPage = 1;
        int defaultSize = 5;

        model = EntityModel
            .of(this, linkTo(methodOn(BookGroupController.class).findById(id)).withSelfRel().withType(methodTypeGET),
                linkTo(methodOn(BookGroupController.class).delete(id)).withRel(deleteRelName)
                    .withType(methodTypeDELETE));
        return model;
    }
}
