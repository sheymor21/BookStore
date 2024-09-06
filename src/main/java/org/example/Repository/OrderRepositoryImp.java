package org.example.Repository;

import org.example.Context.Context;
import org.example.Dto.OrderDTO;
import org.example.Interfaces.OrderRepository;
import org.example.Mapper.OrderMapper;
import org.example.Model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImp implements OrderRepository {
    private final Connection CONNECTION;

    public OrderRepositoryImp() {
        this.CONNECTION = Context.getInstance().getConnection();
    }

    @Override
    public void save(Order order) {
        String sql = "Insert into orders (order_id,user_id,book_id,created_at) values (?,?,?,?)";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, order.Id);
            statement.setString(2, order.UserId);
            statement.setString(3, order.BookId);
            var date = java.sql.Date.valueOf(order.CreatedAt);
            statement.setDate(4, date);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Order order) {
        String sql = "Update orders set user_id=?,book_id=? where order_id=?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, order.UserId);
            statement.setString(2, order.BookId);
            statement.setString(3, order.Id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(String id) {
        String sql = "Delete from orders where order_id= ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDTO get(String id) {
        String sql = "Select u.first_name,u.last_name,b.title,b.author,b.price,o.created_at from orders o inner join users u on o.user_id = u.user_id inner join books b on b.book_id = o.book_id where order_id=?";
        return getOrder(id, sql);
    }

    @Override
    public OrderDTO getByBook(String Title) {
        String sql = "Select u.first_name,u.last_name,b.title,b.author,b.price,o.created_at from orders o inner join users u on o.user_id = u.user_id inner join books b on b.book_id = o.book_id where b.title=?";
        return getOrder(Title, sql);
    }

    @Override
    public OrderDTO getByUser(String Dni) {
        String sql = "Select u.first_name,u.last_name,b.title,b.author,b.price,o.created_at from orders o inner join users u on o.user_id = u.user_id inner join books b on b.book_id = o.book_id where u.dni=?";
        return getOrder(Dni, sql);
    }


    @Override
    public Iterable<OrderDTO> getAll() {
        String sql = "Select u.first_name,u.last_name,b.title,b.author,b.price,o.created_at,o.order_id from orders o inner join users u on o.user_id = u.user_id inner join books b on b.book_id = o.book_id";
        try {
            List<OrderDTO> orders = new ArrayList<>();
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                orders.add(OrderMapper.toOrderDTO(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private OrderDTO getOrder(String parameter, String sql) {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return OrderMapper.toOrderDTO(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
