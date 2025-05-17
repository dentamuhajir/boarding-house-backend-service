package id.kostfinder.app.user.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.response.Response;
import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.repository.AdminRepository;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.PropertyOwnerRepository;
import id.kostfinder.app.user.repository.UserRepository;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private EndUserRepository endUserRepository;
    @Autowired
    private UserRepository userRepository;

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
            //endUser.setProfilePicture("https://picsum.photos/seed/" + faker.number().numberBetween(1, 1001) + "/50/50");
            endUser.setProfilePicture("https://i.pravatar.cc/50?img=" + faker.number().numberBetween(1,70));
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

    @Override
    public GenericResponse getUsers() {

        List<User> users = userRepository.findAll();

//        for(User user: users) {
//            System.out.println(user.getName());
//        }
//
//        Response response = new Response();
//        response .setRc("000");
//        response.setMessage("Success");
//        response.setData(users);

        return GenericResponse.builder()
                .code(200)
                .message("Success")
                .data(users)
                .build();
    }

}
