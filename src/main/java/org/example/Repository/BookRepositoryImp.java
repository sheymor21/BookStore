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
    public void save(Book user) {
        String sql = "Insert into books(id,title,author,releaseyear,createdat) values (?,?,?,?,?)";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, user.Id);
            statement.setString(2, user.Title);
            statement.setString(3, user.Author);
            statement.setInt(4, user.ReleaseYear);
            var date = java.sql.Date.valueOf(user.CreatedAt);
            statement.setDate(5, date);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Book user) {
        String sql = "Update books set title = ?,author=?,releaseyear=? where id = ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, user.Title);
            statement.setString(2, user.Author);
            statement.setInt(3, user.ReleaseYear);
            statement.setString(4, user.Id);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public void delete(String id) {

        String sql = "Delete from books where id = ?";
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
        String sql = "Select * from books where id = ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, id);
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

    @Override
    public Book getByTitle(String title) {
        String sql = "Select * from books where title = ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, title);
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
}
