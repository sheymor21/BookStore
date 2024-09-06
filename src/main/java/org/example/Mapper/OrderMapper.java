package org.example.Mapper;

import org.example.Dto.OrderDTO;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class OrderMapper {
    public static OrderDTO toOrderDTO(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {

            OrderDTO order = new OrderDTO();
            order.setId(resultSet.getString("order_id"));
            order.BookAuthor = resultSet.getString("author");
            order.BookTitle = resultSet.getString("title");
            order.UserName = resultSet.getString("first_name") + " " + resultSet.getString("last_name");
            order.BookPrice = resultSet.getDouble("price");
            order.PurchaseDate = resultSet.getDate("created_at").toLocalDate();
            return order;
        }
        return null;
    }
}
