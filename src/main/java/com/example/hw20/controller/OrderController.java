package com.example.hw20.controller;


import com.example.hw20.model.Order;
import com.example.hw20.model.Product;
import com.example.hw20.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable long id) {
        return orderService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable long id) {
         orderService.deleteOrder(id);
    }
    @PostMapping("/{id}/add-product")
    public Order addProductToOrder(@PathVariable long id, @RequestBody Product product) {
        return orderService.addProductToOrder(id, product);
    }
}