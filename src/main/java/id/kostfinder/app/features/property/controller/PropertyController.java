package id.kostfinder.app.features.property.controller;

import id.kostfinder.app.features.property.service.PropertyService;
import id.kostfinder.app.response.GenericResponse;
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
    @GetMapping(value = "property")
    ResponseEntity<GenericResponse> getProperties() {
        GenericResponse response = propertyService.getProperties();
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "property/total")
    ResponseEntity<GenericResponse> getTotalProperty() {
        GenericResponse response = propertyService.getTotalProperty();
        return ResponseEntity.ok(response);
    }
}
