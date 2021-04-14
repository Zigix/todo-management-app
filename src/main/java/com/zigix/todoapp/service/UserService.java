package com.zigix.todoapp.service;

import com.zigix.todoapp.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public interface UserService {

    default User getCurrentlyLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

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
