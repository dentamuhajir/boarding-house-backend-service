package id.kostfinder.app.features.user.repository;

import id.kostfinder.app.features.user.model.EndUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndUserRepository extends JpaRepository<EndUser, Long> {

}
