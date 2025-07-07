package id.kostfinder.app.features.property.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
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

}
