package id.kostfinder.app.user.service.impl;

import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.repository.AdminRepository;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.PropertyOwnerRepository;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import java.time.LocalDate;

public class UserServiceImpl implements UserService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EndUserRepository endUserRepository;
    @Autowired
    PropertyOwnerRepository propertyOwnerRepository;

    @Override
    public void testUserService() {
        EndUser endUser1 = new EndUser();
        endUser1.setName("Jane Doe");
        endUser1.setEmail("jane.doe@example.com");
        endUser1.setPassword("securePassword123");
        endUser1.setUsername("janedoe");
        endUser1.setProfilePicture("https://example.com/profile.jpg");
        endUser1.setPhoneNumber("08123456789");
        endUser1.setDateOfBirth(LocalDate.of(1995, 4, 15));
        endUser1.setGender("Female");
        endUser1.setOccupation("Software Engineer");
    }
}
