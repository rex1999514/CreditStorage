package com.example.creditstoragebackend.registration;

import com.example.creditstoragebackend.appuser.User;
import com.example.creditstoragebackend.appuser.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;

    @Autowired
    public RegistrationService(UserService userService, EmailValidator emailValidator) {
        this.userService = userService;
        this.emailValidator = emailValidator;
    }

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.
                test(request.getUsername());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        String token = userService.signUpUser(
                new User(
                        request.getUsername(),
                        request.getPassword()

                )
        );

        String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;

        return token;
    }


}
