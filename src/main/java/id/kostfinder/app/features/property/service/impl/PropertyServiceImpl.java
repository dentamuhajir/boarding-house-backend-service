package id.kostfinder.app.features.property.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.features.property.model.Property;
import id.kostfinder.app.features.property.repository.PropertyRepository;
import id.kostfinder.app.features.property.service.PropertyService;
import id.kostfinder.app.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    public GenericResponse seedProperty(){
        Faker faker = new Faker();

        for(int i = 1; i < 30; i++) {
            Property property = new Property();
            property.setName("Kost " + faker.address().streetName());
            property.setAddress("Jl " + faker.address().streetAddress());
            property.setDescription(faker.lorem().sentence());
            propertyRepository.save(property);
        }
        return null;
    }
}
