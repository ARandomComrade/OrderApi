package com.am.orderapi;

import org.springframework.stereotype.Component;

@Component("orderProvider")
public class OrderProvider {
    private final OrderRepository repository;


    public OrderProvider(OrderRepository repository) {
        this.repository = repository;
    }

    public Order post(Order order) {
        return repository.save(order);
    }
}
