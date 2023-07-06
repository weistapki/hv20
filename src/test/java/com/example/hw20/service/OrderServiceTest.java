package com.example.hw20.service;

import com.example.hw20.model.Order;
import com.example.hw20.model.Product;
import com.example.hw20.repository.OrderRepository;
import com.example.hw20.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderService(orderRepository, productRepository);
    }

    @Test
    void getOrderById() {
        long orderId = 1;
        Order order = Order.builder()
                .id(orderId)
                .date(LocalDate.now())
                .build();

        when(orderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(orderId);

        // Check results
        assertNotNull(result); // Check that the result is not null
        assertEquals(orderId, result.getId()); // Check id match
    }

    @Test
    void getOrderByIdNotFound() {
        long orderId = 1;

        when(orderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        // Check that calling getOrderById with orderId throws a ResponseStatusException
        assertThrows(ResponseStatusException.class,
                () -> orderService.getOrderById(orderId));
    }

    @Test
    void getAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(Order.builder().id(1).date(LocalDate.now()).build());
        orders.add(Order.builder().id(2).date(LocalDate.now()).build());
        orders.add(Order.builder().id(3).date(LocalDate.now()).build());

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> result = orderService.getAllOrders();

        // Check results
        assertEquals(orders.size(), result.size()); // Check list size
        assertEquals(orders.get(1).getId(), result.get(1).getId()); // Check id match for the second element
    }

    @Test
    void saveOrder() {
        Order order = Order.builder()
                .id(1)
                .date(LocalDate.now())
                .build();

        when(orderRepository.save(ArgumentMatchers.any(Order.class)))
                .thenReturn(order);

        Order savedOrder = orderService.saveOrder(order);

        // Check results
        assertNotNull(savedOrder); // Check that the saved order is not null
        assertEquals(order, savedOrder); // Check order match between saved and original order
    }

    @Test
    void deleteOrder() {
        long orderId = 1;
        orderService.deleteOrder(orderId);

        // Check that the deleteById method is called once with the orderId argument
        verify(orderRepository, times(1)).deleteById(orderId);
    }

    @Test
    void addProductToOrder() {
        long orderId = 1;
        long productId = 1;
        Product product = Product.builder()
                .id(productId)
                .name("Product")
                .cost(10)
                .build();

        Order order = Order.builder()
                .id(orderId)
                .date(LocalDate.now())
                .build();

        when(orderRepository.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(order));
        when(orderRepository.save(ArgumentMatchers.any(Order.class)))
                .thenReturn(order);

        Order result = orderService.addProductToOrder(orderId, product);

        // Check results
        assertNotNull(result); // Check that the result is not null
        assertEquals(orderId, result.getId()); // Check id match for the order
        assertEquals(product, result.getProduct()); // Check the added product match

        // Check that the findById method is called once with the orderId argument
        verify(orderRepository, times(1)).findById(orderId);
        // Check that the save method is called once with the order argument
        verify(orderRepository, times(1)).save(order);
    }
}
