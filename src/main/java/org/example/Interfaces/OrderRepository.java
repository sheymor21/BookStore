package org.example.Interfaces;

import org.example.Model.Order;

public interface OrderRepository {
    void save(Order order);

    void update(Order order);

    void delete(String id);

    Order get(String id);

    Order getByBook(String Title);

    Order getByUser(String Dni);

    Iterable<Order> getAll();
}
