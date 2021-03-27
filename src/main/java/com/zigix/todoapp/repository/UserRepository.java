package com.zigix.todoapp.repository;

import com.zigix.todoapp.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    User save(User user);
}
