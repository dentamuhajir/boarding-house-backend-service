package id.kostfinder.app.user.controller;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.response.Response;
import id.kostfinder.app.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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
        GenericResponse users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "/users")
    ResponseEntity<?> createEndUser(@RequestBody CreateEndUserRequestDTO request) {
        GenericResponse resp = userService.createEndUser(request);
        return  ResponseEntity.ok(resp.getMessage());
    }


}
