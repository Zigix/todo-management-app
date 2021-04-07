package com.zigix.todoapp.service;

import com.zigix.todoapp.exception.UserNotFoundException;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserRole;
import com.zigix.todoapp.repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with " + username + " username not found"));
    }

    @Override
    public User getUserByUsername(String username) {
        return (User) loadUserByUsername(username);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow();
    }

    @Override
    public User updateUser(User user) {
        if(userRepository.findById(user.getUserId()).isEmpty()) {
            throw new UserNotFoundException("User with id " + user.getUserId() + " not found");
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserById(Long userId) {
        userRepository.deleteByUserId(userId);
    }
}
