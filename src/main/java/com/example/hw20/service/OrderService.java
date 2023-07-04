package com.example.hw20.service;


import com.example.hw20.model.Order;
import com.example.hw20.model.Product;
import com.example.hw20.repository.OrderRepository;
import com.example.hw20.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }

    public Order addProductToOrder(long id, Product product) {
        Order order = getOrderById(id);
        order.setProduct(product);
        return orderRepository.save(order);
    }
}
