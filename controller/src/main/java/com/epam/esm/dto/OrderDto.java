package com.epam.esm.dto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.epam.esm.controller.impl.OrderController;
import com.epam.esm.entity.Book;
import com.epam.esm.entity.BookingClubOrder;
import com.epam.esm.entity.OrderStatus;
import com.epam.esm.entity.User;
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
public class OrderDto {

    @ToString.Exclude
    @Null(message = "{validation.book.id}")
    private Long id;

    private Date createTime;

    private Date endTime;

    private OrderStatus orderStatus;

    private User author;

    private Book book;

    @JsonIgnore
    private EntityModel<OrderDto> model;

    public OrderDto(BookingClubOrder order) {
        this.id = order.getId();
        this.createTime = order.getCreateTime();
        this.endTime = order.getEndTime();
        this.orderStatus = order.getOrderStatus();
        this.author = order.getAuthor();
        this.book = order.getBook();
    }

    public EntityModel<OrderDto> getModel() {
        String deleteRelName = "delete";
        String orderRelName = "orders";
        String methodTypeDELETE = "DELETE";
        String methodTypeGET = "GET";
        int defaultPage = 1;
        int defaultSize = 5;

        model = EntityModel
            .of(this, linkTo(methodOn(OrderController.class).findById(id)).withSelfRel().withType(methodTypeGET),
                linkTo(methodOn(OrderController.class).delete(id)).withRel(deleteRelName).withType(methodTypeDELETE));
        return model;
    }
}
