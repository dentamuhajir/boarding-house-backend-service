package id.kostfinder.app.features.user.repository;

import id.kostfinder.app.features.user.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Long> {
}
