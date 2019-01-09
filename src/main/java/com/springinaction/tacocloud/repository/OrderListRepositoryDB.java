package com.springinaction.tacocloud.repository;


import com.springinaction.tacocloud.model.Order;

import java.util.List;

public interface OrderListRepositoryDB {

    Order findById(Long id);
    List<Order> findAll();

    Order save(Order orderItem);

}
