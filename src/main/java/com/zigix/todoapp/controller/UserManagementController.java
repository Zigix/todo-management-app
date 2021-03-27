package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.projection.UserReadModel;
import com.zigix.todoapp.model.projection.UserWriteModel;
import com.zigix.todoapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/users")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class UserManagementController {

    private final UserService userService;

    public UserManagementController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserReadModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserReadModel getUser(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public UserReadModel addNewUser(@RequestBody UserWriteModel userWriteModel) {
        return userService.addUser(userWriteModel);
    }

    @PutMapping("/{id}")
    public UserReadModel updateUser(@PathVariable("id") Long userId,
                                    @RequestBody UserWriteModel userWriteModel) {
        return userService.updateUser(userId, userWriteModel);
    }
}
