package org.example.Mapper;

import org.example.Model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BookMapper {
    public static Book ToBook(ResultSet resultSet) throws SQLException {
        if (resultSet != null) {
            Book book = new Book();
            book.Id = resultSet.getString("book_id");
            book.Title = resultSet.getString("title");
            book.Author = resultSet.getString("author");
            book.Price = resultSet.getString("price");
            book.ReleaseYear = resultSet.getInt("release_year");
            book.CreatedAt = resultSet.getDate("created_at").toLocalDate();
            return book;
        }
        return null;
    }
}
