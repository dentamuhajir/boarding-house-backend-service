package id.kostfinder.app.features.property.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PropertyFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
