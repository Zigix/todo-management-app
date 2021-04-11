package com.zigix.todoapp.service;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.projection.UserRegistrationRequest;

public interface UserRegistrationService {

    User registerUser(UserRegistrationRequest registrationRequest);

}
