package com.zigix.todoapp.service;

import com.zigix.todoapp.model.ChangePasswordRequest;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.projection.UserReadModel;
import com.zigix.todoapp.model.projection.UserWriteModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserReadModel> getAllUsers();

    UserReadModel getUserById(Long userId);

    UserReadModel getUserByUsername(String username);

    UserReadModel addUser(UserWriteModel userWriteModel);

    UserReadModel updateUser(Long userId, UserWriteModel userWriteModel);

    boolean changeUserPassword(ChangePasswordRequest request, String username);
}
