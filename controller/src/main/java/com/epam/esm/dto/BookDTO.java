package com.epam.esm.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.BookController;
import com.epam.esm.entity.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;
import javax.validation.constraints.Null;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.hateoas.EntityModel;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDTO {

    @ToString.Exclude
    @Null(message = "{validation.book.id}")
    private Long id;

    //@NotNull(message = "{validation.user.name}")
    //@Size(min = 2, max = 70, message = "{validation.user.name.not.null}")
    private String name;

    //@NotNull(message = "{validation.user.surname}")
    //@Size(min = 3, max = 170, message = "{validation.user.surname}")
    private String description;

    private Date creationDate;

    private String author;

    private int count;

    @JsonIgnore
    private EntityModel<BookDTO> model;

    public BookDTO(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.description = book.getDescription();
        this.creationDate = book.getCreationDate();
        this.author = book.getAuthor();
        this.count = book.getCount();
    }

    public EntityModel<BookDTO> getModel() {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";
        int defaultPage = 1;
        int defaultSize = 5;

        model = EntityModel
            .of(this, linkTo(methodOn(BookController.class).findBookById(id)).withSelfRel().withType(methodTypeGET),
                linkTo(methodOn(BookController.class).deleteBook(id)).withRel(deleteRelName)
                    .withType(methodTypeDELETE));
        return model;
    }
}
