package com.springinaction.tacocloud.repository;


import com.springinaction.tacocloud.model.Order;
import com.springinaction.tacocloud.model.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderListRepositoryDB {

    Order findById(Long id);
    List<Order> findAll();

    Order save(Order orderItem);

    Object findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
    //Object findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
