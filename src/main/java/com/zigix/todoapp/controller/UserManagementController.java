package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.UserService;
import org.springframework.data.domain.Pageable;
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

    @GetMapping(params = {"!sort", "!size"})
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping
    public List<User> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @PostMapping
    public User addNewUser(@RequestBody User user) {
        user.setUserId(0L);
        return userService.addUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        user.setUserId(userId);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
    }
}
