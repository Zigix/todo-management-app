package com.zigix.todoapp.model.projection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWriteModel {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
