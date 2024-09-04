package org.example.Interfaces;

import org.example.Model.User;

public interface UserRepository {
    void save(User user);
    void update(User user);
    void delete(String id);
    User get(String dni);
    Iterable<User> getAll();
}
