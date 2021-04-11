package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.UserRegistrationService;
import com.zigix.todoapp.service.UserService;
import com.zigix.todoapp.service.projection.UserReadModel;
import com.zigix.todoapp.service.projection.UserRegistrationRequest;
import com.zigix.todoapp.service.projection.UserUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/management/users")
@PreAuthorize("hasAnyRole('BOSS', 'ADMIN')")
public class UserManagementController {

    private final UserService userService;
    private final UserRegistrationService userRegistrationService;

    public UserManagementController(UserService userService,
                                    UserRegistrationService userRegistrationService) {
        this.userService = userService;
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping(params = {"!sort", "!size"})
    public List<UserReadModel> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<UserReadModel> getAllUsers(Pageable pageable) {
        return userService.getAllUsers(pageable).stream()
                .map(UserReadModel::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserReadModel getUser(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId);
        return new UserReadModel(user);
    }

    @PostMapping
    public UserReadModel registerNewUser(@RequestBody @Valid UserRegistrationRequest registrationRequest) {
        User user =  userRegistrationService.registerUser(registrationRequest);
        return new UserReadModel(user);
    }

    @PutMapping("/{id}")
    public UserReadModel updateUser(@PathVariable("id") Long userId,
                                    @RequestBody @Valid UserUpdateRequest updateRequest) {
        User user = userService.updateUser(userId, updateRequest.toUser());
        return new UserReadModel(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUserById(userId);
    }
}
