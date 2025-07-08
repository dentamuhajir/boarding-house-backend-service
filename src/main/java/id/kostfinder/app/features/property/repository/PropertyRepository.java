package id.kostfinder.app.features.property.repository;

import id.kostfinder.app.features.property.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
