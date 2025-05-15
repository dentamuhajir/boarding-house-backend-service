package id.kostfinder.app.user.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.repository.AdminRepository;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.PropertyOwnerRepository;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        EndUser endUser = new EndUser();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");



        for(int i = 0; i < amount; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String name = firstName + " " + lastName;
            String[] gender = { "Male", "Female"};
            Random generator = new Random();
            int randomIndex = generator.nextInt(gender.length);

            System.out.println("Name: " + name);
            System.out.println("Email: " + firstName.toLowerCase() + lastName.toLowerCase() + "@gmail.com");
            System.out.println("Password :" + faker.internet().password());
            System.out.println("Profile Picture :" + "https://picsum.photos/seed/" + faker.number().numberBetween(1, 1001) + "/50/50");
            System.out.println("Gender: " + gender[randomIndex] );
            System.out.println("Date of Birth :" + sdf.format(faker.date().birthday()));
            System.out.println("Username: " + firstName.toLowerCase() + lastName.toLowerCase());
            System.out.println("Phone Number: " + faker.phoneNumber().cellPhone());
            System.out.println("Occupation: " + faker.job().title());

            System.out.println("=============================================");
        }

    }

    @Override
    public void generateDummyDataPropertyOwner() {

    }

}
