package org.example.Services;


import org.example.Interfaces.UserRepository;
import org.example.Model.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.update(user);
    }

    public void deleteUser(String id) {
        userRepository.delete(id);
    }

    public User getUser(String id) {
        return userRepository.get(id);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.getAll();
    }


}
