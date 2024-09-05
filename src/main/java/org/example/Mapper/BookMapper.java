package org.example.Mapper;

import org.example.Model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BookMapper {
    public static Book ToBook(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            Book book = new Book();
            book.Id = resultSet.getString("id");
            book.Title = resultSet.getString("title");
            book.Author = resultSet.getString("author");
            book.ReleaseYear = resultSet.getInt("releaseyear");
            book.CreatedAt = resultSet.getDate("createdat").toLocalDate();
            return book;
        }
        return null;
    }
}
