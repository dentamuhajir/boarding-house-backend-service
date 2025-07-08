package id.kostfinder.app.features.property.repository;

import id.kostfinder.app.features.property.model.PropertyFacility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyFacilityRepository extends JpaRepository<PropertyFacility, Long> {
}
