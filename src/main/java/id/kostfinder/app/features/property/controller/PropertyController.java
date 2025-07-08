package id.kostfinder.app.features.property.controller;

import id.kostfinder.app.features.property.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class PropertyController {
    PropertyService propertyService;

    @GetMapping(value = "property/seed")
    ResponseEntity<?> seedProperty() {
        propertyService.seedProperty();
        return ResponseEntity.ok("Success");
    }
}
