package com.zigix.todoapp.service.registration;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordGeneratorService implements PasswordGeneratorService {

    @Override
    public String generate() {
        return RandomString.make(15);
    }

}
