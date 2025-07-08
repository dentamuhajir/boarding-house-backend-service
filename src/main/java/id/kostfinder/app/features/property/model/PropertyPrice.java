package id.kostfinder.app.features.property.model;

import jakarta.persistence.*;

@Entity
public class PropertyPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double price;

    @Enumerated(EnumType.STRING) // store in string type
    private PeriodType period;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;
}

enum PeriodType {
    DAILY,
    MONTHLY,
    YEARLY
}
