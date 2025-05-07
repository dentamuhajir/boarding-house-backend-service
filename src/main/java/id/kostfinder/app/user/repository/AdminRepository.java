package id.kostfinder.app.user.repository;

import id.kostfinder.app.user.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
