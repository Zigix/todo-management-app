package com.zigix.todoapp.service.impl;

import com.zigix.todoapp.model.User;
import com.zigix.todoapp.model.UserRole;
import com.zigix.todoapp.service.UserRegistrationService;
import com.zigix.todoapp.service.UserService;
import com.zigix.todoapp.service.projection.UserRegistrationRequest;
import com.zigix.todoapp.service.registration.EmailSender;
import com.zigix.todoapp.service.registration.PasswordGeneratorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserService userService;
    private final EmailSender emailSender;
    private final PasswordGeneratorService passwordGeneratorService;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(UserService userService,
                                       EmailSender emailSender,
                                       PasswordGeneratorService passwordGeneratorService,
                                       PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.emailSender = emailSender;
        this.passwordGeneratorService = passwordGeneratorService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User registerUser(UserRegistrationRequest registrationRequest) {
        String firstName = registrationRequest.getFirstName();
        String lastName = registrationRequest.getLastName();
        String emailAddress = registrationRequest.getEmail();

        String username = createValidUsername(firstName, lastName); // FIXME ...
        String password = passwordGeneratorService.generate();

        Map<String, String> exchangers = new HashMap<>(); // FIXME: ...
        exchangers.put("@$!name!$@", firstName + " " + lastName);
        exchangers.put("@$!password!$@", password);

        String mailContent = prepareMailContent(exchangers);

        emailSender.sendEmail(emailAddress, mailContent);

        return userService.addUser(
                User.builder()
                        .firstName(firstName)
                        .lastName(lastName)
                        .username(username)
                        .password(passwordEncoder.encode(password))
                        .email(emailAddress)
                        .roles(List.of(UserRole.EMPLOYEE))
                        .build()
        );
    }

    private String prepareMailContent(Map<String, String> exchangers) {
        String path = "src/main/resources/static/email-template.html"; // FIXME: ...
        File file = new File(path);
        StringBuilder mailContentBuilder;
        String mailContent;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            mailContentBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                mailContentBuilder.append(line);
            }

            mailContent = mailContentBuilder.toString();

            for (String key : exchangers.keySet()) {
                mailContent = mailContent.replace(key, exchangers.get(key));
            }

        } catch (IOException e) {
            throw new RuntimeException(e); // FIXME: ...
        }

        return mailContent;
    }

    private String createValidUsername(String firstName, String lastName) {
        String username = firstName.toLowerCase().charAt(0) + lastName.toLowerCase();
        int counter = 2;
        do {
            if (userService.existsByUsername(username)) {
                username += counter;
                counter++;
            } else {
                break;
            }
        } while (true);

        return username;
    }
}
