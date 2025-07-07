package id.kostfinder.app.features.user.repository;

import id.kostfinder.app.features.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying // Always add this annotation for update because if you not add this the default is select and produce error
    @Transactional // If you no add it will produce error 500 "Executing an update/delete query;"
    @Query("UPDATE User u SET u.deleted = true WHERE u. id = :id AND u.deleted = false") // Refer to entity User not table user
    int softDeleteById(@Param("id") Long id); // why return is int because if you operation to update or modifying the return int is indicated of the row affected
    public User findByEmail(String email);
}
