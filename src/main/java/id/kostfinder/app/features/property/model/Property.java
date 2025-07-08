package id.kostfinder.app.features.property.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String facility;
    private String address;

    // mappedBy propwerty must refers to  private Property property; the variable defined
    @OneToMany(mappedBy = "property")
    private List<PropertyImage> propertyImages;

    @OneToMany(mappedBy = "property")
    private List<PropertyPrice> propertyPrices;

}
