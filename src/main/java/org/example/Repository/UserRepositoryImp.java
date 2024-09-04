package org.example.Repository;

import org.example.Context.Context;
import org.example.Interfaces.UserRepository;
import org.example.Mapper.UserMapper;
import org.example.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImp implements UserRepository {
    private final Connection CONNECTION;

    public UserRepositoryImp() {
        this.CONNECTION = Context.getInstance().getConnection();
    }

    @Override
    public void save(User user) {
        try {

            String sql = "Insert into users(id,dni,firstname,lastname,email,createdat) values (?,?,?,?,?,?)";
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, user.Id);
            statement.setString(2, user.Dni);
            statement.setString(3, user.FirstName);
            statement.setString(4, user.LastName);
            statement.setString(5, user.Email);
            var date = java.sql.Date.valueOf(user.CreatedAt);
            statement.setDate(6, date);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            String sql = "Update users set dni = ?,firstname=?,lastname=?,email=? where id = ?";
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, user.Dni);
            statement.setString(2, user.FirstName);
            statement.setString(3, user.LastName);
            statement.setString(4, user.Email);
            statement.setString(5, user.Id);
            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(String id) {
        String sql = "Delete from users where id = ?";
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
    public User get(String dni) {
        String sql = "Select * from users where dni = ?";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            statement.setString(1, dni);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                return UserMapper.ToUser(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Iterable<User> getAll() {
        String sql = "Select * from users";
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                User user = UserMapper.ToUser(resultSet);
                users.add(user);

            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
