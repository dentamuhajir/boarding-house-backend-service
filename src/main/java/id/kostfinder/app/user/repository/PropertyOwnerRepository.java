package id.kostfinder.app.user.repository;

import id.kostfinder.app.user.model.PropertyOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyOwnerRepository extends JpaRepository<PropertyOwner, Long> {
}
