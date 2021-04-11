package com.zigix.todoapp.repository;

import com.zigix.todoapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);

    User save(User user);

    void deleteByUserId(Long userId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
