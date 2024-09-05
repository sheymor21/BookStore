package org.example.Repository;

import org.example.Context.Context;
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
        String sql = "Insert into orders (id,userid,bookid,createdat) values (?,?,?,?)";
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
        String sql = "Update orders set userid=?,bookid=? where id=?";
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
        String sql = "Delete from orders where id=?";
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
    public Order get(String id) {
        String sql = "Select * from orders where id=?";
        return getOrder(id, sql);
    }

    @Override
    public Order getByBook(String Title) {
        String sql = "Select o.bookid,o.id,o.userid,o.createdat from orders o inner join books b on b.id=o.bookid where b.title=?";
        return getOrder(Title, sql);
    }

    @Override
    public Order getByUser(String Dni) {
        String sql = "Select o.bookid,o.id,o.userid,o.createdat from orders o inner join users u on u.id=o.userid where u.dni=?";
        return getOrder(Dni, sql);
    }

    private Order getOrder(String parameter, String sql) {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return OrderMapper.toOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Iterable<Order> getAll() {
        String sql = "Select * from orders";
        try {
            List<Order> orders = new ArrayList<>();
            Statement statement = CONNECTION.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                orders.add(OrderMapper.toOrder(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
