package com.epam.esm.dto.book;

import com.epam.esm.controller.impl.BookController;
import com.epam.esm.dto.AuthorDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.EntityModel;

import java.util.Date;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto extends UpdateBookDto {

    private String creationDate;
    private List<AuthorDto> authors;

    @JsonIgnore
    @ToString.Exclude
    private EntityModel<BookDto> model;

    public EntityModel<BookDto> getModel() {
        String deleteRelName = "delete";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";

        model = EntityModel
            .of(this,
                    linkTo(methodOn(BookController.class)
                            .findById(id))
                            .withSelfRel()
                            .withType(methodTypeGET),
                    linkTo(methodOn(BookController.class)
                            .delete(id))
                            .withRel(deleteRelName)
                            .withType(methodTypeDELETE));
        return model;
    }

    public BookDto(Long id,
                   String name,
                   String description,
                   String creationDate,
                   int count, List<AuthorDto> authors,
                   String yearTheBookWasPrinted) {
        super(id, name, description, count, yearTheBookWasPrinted);
        this.id = id;
        this.creationDate = creationDate;
        this.authors = authors;
    }
}
