package sinara_project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sinara_project.models.UserApp;
import sinara_project.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public UserApp register(@RequestBody UserApp user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserApp user) {
        return userService.verify(user);
    }
}
