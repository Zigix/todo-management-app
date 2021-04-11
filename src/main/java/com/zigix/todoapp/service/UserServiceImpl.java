package com.zigix.todoapp.service;

import com.zigix.todoapp.exception.UserNotFoundException;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserRole;
import com.zigix.todoapp.repository.UserRepository;
import com.zigix.todoapp.service.projection.UserRegistrationRequest;
import com.zigix.todoapp.service.registration.PasswordGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordGenerator passwordGenerator;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           PasswordGenerator passwordGenerator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
    }

    @PostConstruct
    public void initBossAndAdminAccount() {
        if(userRepository.findByUsername("boss123").isEmpty()) {
            User boss = User.builder()
                    .username("boss123")
                    .password(passwordEncoder.encode("boss123"))
                    .email("boss@gmail.com")
                    .firstName("Alex")
                    .lastName("XD")
                    .roles(List.of(UserRole.BOSS, UserRole.ADMIN))
                    .build();
            userRepository.save(boss);
        }
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User " + username + " not found"));
    }

    @Override
    public User getCurrentlyLoggedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

    @Override
    public User updateUser(Long userId, User user) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User with id " + user.getUserId() + " not found");
        }
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
