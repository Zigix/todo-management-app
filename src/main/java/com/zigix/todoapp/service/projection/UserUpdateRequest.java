package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserRole;
import com.zigix.todoapp.service.registration.UniqueEmail;

import javax.validation.constraints.Email;
import java.util.List;

public class UserUpdateRequest {

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<UserRole> authorities;

    @Email
    @UniqueEmail
    private String email;

    public User toUser() {
        return User.builder()
                .firstName(this.firstName)
                .lastName(this.lastName)
                .username(this.username)
                .password(this.password)
                .email(this.email)
                .roles(this.authorities)
                .build();
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<UserRole> authorities) {
        this.authorities = authorities;
    }
}
