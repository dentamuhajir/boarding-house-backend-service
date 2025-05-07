package id.kostfinder.app.user.service.impl;

import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.repository.AdminRepository;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.PropertyOwnerRepository;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    EndUserRepository endUserRepository;
    @Autowired
    PropertyOwnerRepository propertyOwnerRepository;

}
