package id.kostfinder.app.test;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/ping")
    ResponseEntity<String> ping() {
        return ResponseEntity.ok("All is well");
    }
}
