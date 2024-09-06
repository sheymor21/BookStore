package org.example.Repository;

import org.example.Context.Context;
import org.example.Interfaces.BookRepository;
import org.example.Mapper.BookMapper;
import org.example.Model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImp implements BookRepository {
    private final Connection CONNECTION;

    public BookRepositoryImp() {
        this.CONNECTION = Context.getInstance().getConnection();
    }

    @Override
    public void save(Book book) {
        String sql = "INSERT INTO books (book_id, title, author,price, release_year, created_at) VALUES (?, ?, ?, ?, ?,?)";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, book.Id);
            statement.setString(2, book.Title);
            statement.setString(3, book.Author);
            statement.setString(4, book.Price);
            statement.setInt(5, book.ReleaseYear);
            var date = java.sql.Date.valueOf(book.CreatedAt);
            statement.setDate(6, date);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book book) {
        String sql = "Update books set title = ?,author=?,release_year=? where book_id = ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, book.Title);
            statement.setString(2, book.Author);
            statement.setInt(3, book.ReleaseYear);
            statement.setString(4, book.Id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(String id) {

        String sql = "Delete from books where book_id = ?";
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
    public Book get(String id) {
        String sql = "Select * from books where book_id = ?";
        return getBook(id, sql);
    }


    @Override
    public Iterable<Book> getAll() {
        String sql = "Select * from books";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                books.add(BookMapper.ToBook(resultSet));
            }
            return books;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private Book getBook(String parameter, String sql) {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, parameter);
            ResultSet resultSet = statement.executeQuery();
            statement.close();
            if (resultSet.next()) {

                return BookMapper.ToBook(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
