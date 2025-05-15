package id.kostfinder.app.user.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.repository.AdminRepository;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.PropertyOwnerRepository;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EndUserRepository endUserRepository;

    @Override
    public String testUserService() {
        EndUser endUser1 = new EndUser();
//        endUser1.setId(1L);
        endUser1.setName("Jane Doe");
        endUser1.setEmail("jane.doe@example.com");
        endUser1.setPassword("securePassword123");
        endUser1.setUsername("janedoe");
        endUser1.setProfilePicture("https://example.com/profile.jpg");
        endUser1.setPhoneNumber("08123456789");
        endUser1.setDateOfBirth(LocalDate.of(1995, 4, 15));
        endUser1.setGender("Female");
        endUser1.setOccupation("Software Engineer");
        endUser1 = endUserRepository.save(endUser1);
        if(endUser1 != null){
            return "Data save successfully";
        } else {
            return "Data cannot save";
        }
    }

    @Override
    public void generateDummyDataEndUser(int amount) {
        Faker faker = new Faker();

        for(int i = 0; i < amount; i++) {
            EndUser endUser = new EndUser();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = faker.date().birthday();
            LocalDate localBirthday = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String name = firstName + " " + lastName;
            String[] gender = { "Male", "Female"};
            Random generator = new Random();
            int randomIndex = generator.nextInt(gender.length);

            endUser.setName(name);
            endUser.setEmail(firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com");
            endUser.setPassword(faker.internet().password());
            endUser.setProfilePicture("https://picsum.photos/seed/" + faker.number().numberBetween(1, 1001) + "/50/50");
            endUser.setGender(gender[randomIndex]);
            endUser.setUsername(firstName.toLowerCase() + lastName.toLowerCase());
            endUser.setPhoneNumber(faker.phoneNumber().cellPhone());
            endUser.setDateOfBirth(localBirthday);
            endUser.setOccupation(faker.job().title());
            endUserRepository.save(endUser);
        }


    }

    @Override
    public void generateDummyDataPropertyOwner() {

    }

}
