package com.epam.esm.service.support.impl;

import org.springframework.stereotype.Component;

@Component
public class OrderPojoConverter /*implements PojoConverter<CertificateOrderPOJO, Order>*/ {

    /*@Override
    public List<CertificateOrderPOJO> convert(List<Order> orders) {
        return orders
            .stream()
            .map(CertificateOrderPOJO::new)
            .collect(Collectors.toList());
    }

    @Override
    public Order convert(CertificateOrderPOJO order) {
        return new Order(order.getCost(), order.getDescription(),
                                    order.getCreatedDate()
        );
    }*/
}
