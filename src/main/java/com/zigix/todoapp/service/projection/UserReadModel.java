package com.zigix.todoapp.service.projection;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserRole;

import java.util.List;

public class UserReadModel {

    private Long userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private List<UserRole> roles;

    public UserReadModel(User source) {
        this.userId = source.getUserId();
        this.firstName = source.getFirstName();
        this.lastName = source.getLastName();
        this.username = source.getUsername();
        this.email = source.getEmail();
        this.roles = source.getRoles();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }
}
