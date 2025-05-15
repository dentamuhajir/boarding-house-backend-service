package id.kostfinder.app.test;


import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.ok("All is well");
    }
    @GetMapping("/test-user-service")
    ResponseEntity<?> testUserService() {
        String result = userService.testUserService();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/test/generate/user")
    ResponseEntity<?> testGenerateUser() {
        userService.generateDummyDataEndUser(10);
        return ResponseEntity.ok("");
    }
}
