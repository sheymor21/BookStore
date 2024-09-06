package org.example.Services;

import org.example.Dto.OrderDTO;
import org.example.Interfaces.OrderRepository;
import org.example.Model.Order;

import java.util.List;
import java.util.stream.StreamSupport;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public void update(Order order) {
        orderRepository.update(order);
    }

    public void delete(String id) {
        orderRepository.delete(id);
    }

    public OrderDTO get(String id) {
        return orderRepository.get(id);
    }

    public OrderDTO getByBook(String Title) {
        return orderRepository.getByBook(Title);
    }

    public OrderDTO getByUser(String Dni) {
        return orderRepository.getByUser(Dni);
    }

    public List<OrderDTO> getAll() {
        Iterable<OrderDTO> orders = orderRepository.getAll();
        return StreamSupport.stream(orders.spliterator(), false).toList();
    }

}
