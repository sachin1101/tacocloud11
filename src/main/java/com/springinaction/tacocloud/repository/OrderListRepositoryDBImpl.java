package com.springinaction.tacocloud.repository;

import com.springinaction.tacocloud.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Repository
public class OrderListRepositoryDBImpl implements  OrderListRepositoryDB {


    private Long orderId;

    private JdbcTemplate jdbc;

    @Autowired
    public OrderListRepositoryDBImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Order findById(Long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Order save(Order orderItem) {
        return null;
    }


    @PostConstruct
    private void  initTacoCounter()
    {
        orderId = jdbc.queryForObject("select nvl(max(id),0) FROM taco", Long.class);

        log.info("Taco Counter in DB is ::" + orderId);
        orderId++;
    }
}
