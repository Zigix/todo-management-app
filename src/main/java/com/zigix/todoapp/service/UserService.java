package com.zigix.todoapp.service;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.projection.UserRegistrationRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    User getCurrentlyLoggedUser();

    List<User> getAllUsers();

    List<User> getAllUsers(Pageable pageable);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(Long userId, User user);

    void deleteUserById(Long userId);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}
