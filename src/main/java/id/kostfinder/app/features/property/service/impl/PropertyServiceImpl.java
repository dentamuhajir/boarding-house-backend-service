package id.kostfinder.app.features.property.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.exception.GeneralException;
import id.kostfinder.app.features.property.model.Property;
import id.kostfinder.app.features.property.model.PropertyFacility;
import id.kostfinder.app.features.property.repository.PropertyFacilityRepository;
import id.kostfinder.app.features.property.repository.PropertyRepository;
import id.kostfinder.app.features.property.service.PropertyService;
import id.kostfinder.app.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class PropertyServiceImpl implements PropertyService {
    private PropertyRepository propertyRepository;
    private PropertyFacilityRepository propertyFacilityRepository;

    public GenericResponse seedProperty(){
        Faker faker = new Faker();
        List<String> facilities = new ArrayList<>();
        facilities.add("AC");
        facilities.add("Wastafel");
        facilities.add("Desk");
        facilities.add("Bed");
        facilities.add("WiFi");
        facilities.add("Shower");
        facilities.add("Breakfast");
        facilities.add("Window");

        //List<PropertyFacility> storeFacility = new ArrayList<>();
        for(int i = 1; i <= 10; i++) {
            System.out.println("hit there");
            Property property = new Property();

            Collections.shuffle(facilities);

            property.setName("Kost " + faker.address().cityName());
            property.setAddress("Jl " + faker.address().streetAddress());
            property.setDescription(faker.lorem().sentence());
            property.setPhoto("https://picsum.photos/seed/" + faker.number().numberBetween(1, 1001) + "/600/400");
            propertyRepository.save(property);

            int randomTotalFacility = faker.number().numberBetween(1, facilities.size());
            for(int j = 0; j < randomTotalFacility; j++) {
                // shuffle the list data of facilities

                PropertyFacility propertyFacility = new PropertyFacility();
                // add value to propertyFacility
                propertyFacility.setName(facilities.get(j));
                propertyFacility.setProperty(property);
                //storeFacility.add(propertyFacility);
                propertyFacilityRepository.save(propertyFacility);
            }
        }
        return null;
    }

    @Override
    public GenericResponse getProperties() {

        List<Property> property = propertyRepository.findAll();
        return GenericResponse.builder()
                .code(200)
                .success(true)
                .message("Get a list of property")
                .data(property)
                .build();
    }

    public GenericResponse getTotalProperty() {
        Long totalProperty = 0L;
        Map<String, Long> result = new HashMap<>();

        try {
            totalProperty = propertyRepository.count();
            result.put("totalProperty", totalProperty);
        } catch (Exception e) {
            throw new GeneralException(500, e.getMessage());
        }
        return GenericResponse.builder()
                .success(true)
                .code(200)
                .data(result)
                .build();
    }
}
