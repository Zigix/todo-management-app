package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.service.registration.UniqueEmail;

import javax.validation.constraints.Email;

public class UserRegistrationRequest {

    private String firstName;
    private String lastName;

    @Email
    @UniqueEmail
    private String email;

    public UserRegistrationRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
