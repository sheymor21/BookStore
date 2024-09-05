package org.example.Mapper;

import org.example.Model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class OrderMapper {
    public static Order toOrder(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {

            Order order = new Order();
            order.Id = resultSet.getString("id");
            order.BookId = resultSet.getString("bookid");
            order.UserId = resultSet.getString("userid");
            order.CreatedAt = resultSet.getDate("createdat").toLocalDate();
            return order;
        }
        return null;
    }
}
