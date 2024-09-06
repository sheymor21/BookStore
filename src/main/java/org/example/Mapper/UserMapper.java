package org.example.Mapper;

import org.example.Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserMapper {
    public static User ToUser(ResultSet set) throws SQLException {
        if (set != null) {
            User user = new User();
            user.Id = set.getString("user_id");
            user.Dni = set.getString("dni");
            user.FirstName = set.getString("first_name");
            user.LastName = set.getString("last_name");
            user.Email = set.getString("email");
            user.CreatedAt = set.getDate("created_at").toLocalDate();
            return user;
        }
        return null;
    }
}
