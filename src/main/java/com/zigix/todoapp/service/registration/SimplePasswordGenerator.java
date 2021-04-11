package com.zigix.todoapp.service.registration;

import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Component;

@Component
public class SimplePasswordGenerator implements PasswordGenerator {

    @Override
    public String generate() {
        return RandomString.make(15);
    }

}
