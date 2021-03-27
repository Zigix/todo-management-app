package com.zigix.todoapp.model.projection;

import com.zigix.todoapp.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReadModel {

    private Long userId;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private List<UserRole> roles;
}
