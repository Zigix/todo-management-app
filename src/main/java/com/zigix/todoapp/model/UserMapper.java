package com.zigix.todoapp.model;

import com.zigix.todoapp.model.projection.UserReadModel;
import com.zigix.todoapp.model.projection.UserWriteModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserWriteModel userWriteModel) {
        return User.builder()
                    .username(userWriteModel.getUsername())
                    .password(userWriteModel.getPassword())
                    .email(userWriteModel.getEmail())
                    .firstName(userWriteModel.getFirstName())
                    .lastName(userWriteModel.getLastName())
                    .build();
    }

    public UserReadModel toUserReadModel(User user) {
        return UserReadModel.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .build();
    }
}
