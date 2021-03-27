package com.zigix.todoapp.adapter;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryAdapter
        extends UserRepository, JpaRepository<User, Long> {

}
