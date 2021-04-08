package com.epam.esm.service;

import com.epam.esm.pojo.CertificateOrderPOJO;
import com.epam.esm.pojo.UserPOJO;
import java.util.List;

public interface OrderService {

    /**
     * This method finds all orders from database
     **/
    List<CertificateOrderPOJO> findAll(int page, int size);

    /**
     * This method finds concrete Order By Id
     *
     * @param id Tag Id
     * @return Tag
     **/
    CertificateOrderPOJO find(long id);

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
    CertificateOrderPOJO create(CertificateOrderPOJO order, UserPOJO userPOJO);

    /**
     * This method finds all orders from database which belong concrete user
     **/
    List<CertificateOrderPOJO> findAllByOwner(long id, int offset, int limit);

    /**
     * This method finds all orders from database which belong concrete user without pagination
     **/
    List<CertificateOrderPOJO> findAllByOwner(long id);


    /**
     * This method finds count all orders from database without pagination
     **/
    int ordersCountByOwner(long id);

    /**
     * This method add certificates to order
     **/
    CertificateOrderPOJO addCertificates(long OrderId, List<Long> certificatesId);

    /**
     * This method finds count all orders from database without pagination
     **/
    int getOrdersCount();
}
