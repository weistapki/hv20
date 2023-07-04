package com.example.hw20.repository;


import com.example.hw20.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
}

