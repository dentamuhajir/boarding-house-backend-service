package id.kostfinder.app.features.property.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import jakarta.persistence.*;

@Entity
public class PropertyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private ImageType type;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

}

enum ImageType {
    MAIN,
    GALLERY
}
