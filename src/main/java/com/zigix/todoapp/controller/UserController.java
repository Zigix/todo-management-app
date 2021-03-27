package com.zigix.todoapp.controller;

import com.zigix.todoapp.model.ChangePasswordRequest;
import com.zigix.todoapp.model.projection.UserReadModel;
import com.zigix.todoapp.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/users/profile")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserReadModel showUserProfileInfo(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @PatchMapping
    public boolean changeUserPassword(@RequestBody ChangePasswordRequest request,
                                      Principal principal) {
        return userService.changeUserPassword(request, principal.getName());
    }
}
