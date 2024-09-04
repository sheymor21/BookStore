package org.example.Mapper;

import org.example.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserMapper {
    public static User ToUser(ResultSet set) throws SQLException {
        if (set != null) {
            User user = new User();
            user.Id = set.getString("id");
            user.Dni = set.getString("dni");
            user.FirstName = set.getString("firstname");
            user.LastName = set.getString("lastname");
            user.Email = set.getString("email");
            user.CreatedAt = set.getDate("createdat").toLocalDate();
            return user;
        }
        return null;
    }
}
