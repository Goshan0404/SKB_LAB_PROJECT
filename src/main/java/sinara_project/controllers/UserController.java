package sinara_project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sinara_project.models.user.UserApp;
import sinara_project.models.user.UserRegisterDto;
import sinara_project.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserApp register(@Valid @RequestBody UserRegisterDto user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRegisterDto user) {
        return userService.verify(user);
    }
}
