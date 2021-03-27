package com.zigix.todoapp.service;

import com.zigix.todoapp.exception.UserNotFoundException;
import com.zigix.todoapp.model.ChangePasswordRequest;
import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserMapper;
import com.zigix.todoapp.model.UserRole;
import com.zigix.todoapp.model.projection.UserReadModel;
import com.zigix.todoapp.model.projection.UserWriteModel;
import com.zigix.todoapp.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
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
                        new UsernameNotFoundException("User with " + username + " username does not exist"));
    }

    @Override
    public UserReadModel getUserByUsername(String username) {
        User user = (User) loadUserByUsername(username);
        return userMapper.toUserReadModel(user);
    }

    @Override
    public List<UserReadModel> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserReadModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserReadModel addUser(UserWriteModel userWriteModel) {
        User user = userMapper.toUser(userWriteModel);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of(UserRole.EMPLOYEE));
        return userMapper.toUserReadModel(userRepository.save(user));
    }

    @Override
    public UserReadModel getUserById(Long userId) {
        User user =  userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
        return userMapper.toUserReadModel(user);
    }

    @Override
    public UserReadModel updateUser(Long userId, UserWriteModel userWriteModel) {
        if(userRepository.findById(userId).isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found");
        }

        User user = userMapper.toUser(userWriteModel);
        user.setUserId(userId);

        return userMapper.toUserReadModel(userRepository.save(user));
    }

    @Override
    @Transactional
    public boolean changeUserPassword(ChangePasswordRequest request, String username) {
        User user = (User) loadUserByUsername(username);
        if(!passwordEncoder.encode(request.getCurrentPassword()).equals(user.getPassword())) {
            return false;
        }
        if(!request.getNewPassword().equals(request.getMatchingNewPassword())) {
            return false;
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        return true;
    }
}
