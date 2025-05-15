package id.kostfinder.app.user.controller;

import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users/generated/end-user")
    ResponseEntity<?> generatedEndUser() {
        userService.generateDummyDataEndUser(10);
        return ResponseEntity.ok("");
    }

    @GetMapping(value = "/users")
    ResponseEntity<?> getUsers() {
        List<?> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }


}
