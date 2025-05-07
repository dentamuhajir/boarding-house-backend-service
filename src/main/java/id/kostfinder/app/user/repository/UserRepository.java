package id.kostfinder.app.user.repository;

import id.kostfinder.app.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
