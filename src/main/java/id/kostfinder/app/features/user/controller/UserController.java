package id.kostfinder.app.features.user.controller;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.features.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.features.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    // localhost:8081/users?page=0&size=10&sort=id,desc
    ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        Sort sorting =Sort.by(Sort.Direction.fromString(sort[1]), sort[0]); // sort[0] = id, sort[1 = asc/desc
        Pageable pageable = PageRequest.of(page, size, sorting);
        GenericResponse users = userService.getUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/users/total")
    ResponseEntity<?> getTotalUsers() {
        GenericResponse totalUsers = userService.getTotalUsers();
        return ResponseEntity.ok(totalUsers);
    }

    @PostMapping(value = "/users")
    ResponseEntity<?> createEndUser(@Valid @RequestBody CreateEndUserRequestDTO request) {
        GenericResponse resp = userService.createEndUser(request);
        return  ResponseEntity.ok(resp.getMessage());
    }

    @GetMapping(value = "/users/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        GenericResponse user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping(value = "/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        GenericResponse user = userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }
}
