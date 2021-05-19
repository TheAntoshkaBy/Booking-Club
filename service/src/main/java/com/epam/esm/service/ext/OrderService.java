package com.epam.esm.service.ext;

import com.epam.esm.entity.BookingClubOrder;
import com.epam.esm.entity.User;
import java.util.List;

public interface OrderService extends BookingClubService<BookingClubOrder>{

    /**
     * This method finds all orders from database
     **/
    List<BookingClubOrder> findAll(int page, int size);

    /**
     * This method finds concrete Order By Id
     *
     * @param id Tag Id
     * @return Tag
     **/
    BookingClubOrder findById(long id);

    /**
     * This method delete order by transmitted user id
     *
     * @param id user
     **/
    void delete(long id);

    /**
     * This method add new Order
     *
     * @param order User object
     **/
    BookingClubOrder create(BookingClubOrder order, User user);

    BookingClubOrder create(BookingClubOrder bookingClubOrder);


    BookingClubOrder update(BookingClubOrder bookingClubOrder, long id);

    /**
     * This method finds all orders from database which belong concrete user
     **/
    List<BookingClubOrder> findAllByOwner(long id, int offset, int limit);

    /**
     * This method finds count all orders from database without pagination
     **/
    int ordersCountByOwner(long id);

    /**
     * This method finds count all orders from database without pagination
     **/
    int getOrdersCount();
}
