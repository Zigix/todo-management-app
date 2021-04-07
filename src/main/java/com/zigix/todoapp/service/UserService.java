package com.zigix.todoapp.service;

import com.zigix.todoapp.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    List<User> getAllUsers(Pageable pageable);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    User addUser(User user);

    User updateUser(User user);

    void deleteUserById(Long userId);
}
