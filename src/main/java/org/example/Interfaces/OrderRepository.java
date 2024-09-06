package org.example.Interfaces;

import org.example.Dto.OrderDTO;
import org.example.Model.Order;

public interface OrderRepository {
    void save(Order order);

    void update(Order order);

    void delete(String id);

    OrderDTO get(String id);

    OrderDTO getByBook(String Title);

    OrderDTO getByUser(String Dni);

    Iterable<OrderDTO> getAll();
}
