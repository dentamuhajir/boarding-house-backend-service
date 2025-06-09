package id.kostfinder.app.user.service.impl;

import com.github.javafaker.Faker;
import id.kostfinder.app.exception.GeneralException;
import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.user.dto.request.CreateEndUserRequestDTO;
import id.kostfinder.app.user.dto.response.UserDetailResponseDTO;
import id.kostfinder.app.user.dto.response.UserListResponseDTO;
import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.model.User;
import id.kostfinder.app.user.repository.EndUserRepository;
import id.kostfinder.app.user.repository.UserRepository;
import id.kostfinder.app.user.service.UserService;
import jakarta.persistence.MapsId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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
            endUser.setDeleted(false);
            endUserRepository.save(endUser);
        }
    }

    @Override
    public void generateDummyDataPropertyOwner() {

    }

    @Override
    public GenericResponse getUsers(Pageable pageable) {

        //userRepository = null; // simulate to hit throw
        try {
            Page<User> users = userRepository.findAll(pageable);
            //List<UserListResponseDTO> dtos = new ArrayList<>();
            List<UserListResponseDTO> dtos = users.stream().map(user -> {
                EndUser endUser = (EndUser) user;
                return UserListResponseDTO.builder()
                                .id(endUser.getId())
                                .name(endUser.getName())
                                .email(endUser.getEmail())
                                .phoneNumber(endUser.getPhoneNumber())
                                .profilePicture(endUser.getProfilePicture())
                        .build();
            }).toList();

//            for(User user: users) {
////                UserListResponseDTO dto = new UserListResponseDTO();
//                EndUser endUser = (EndUser) user;
//                dtos.add(UserListResponseDTO.builder()
//                                .id(endUser.getId())
//                                .name(endUser.getName())
//                                .email(endUser.getEmail())
//                                .phoneNumber(endUser.getPhoneNumber())
//                                .profilePicture(endUser.getProfilePicture())
//                        .build());
////                dto.setName(user.getName());
////                dto.setEmail(user.getEmail());
////                dto.setPhoneNumber(endUser.getPhoneNumber());
////                dto.setProfilePicture(endUser.getProfilePicture());
////                dtos.add(dto);
//            }

            return GenericResponse.builder()
                    .success(true)
                    .message("Request list of users is successful")
                    .code(200)
                    .data(Map.of(
                            "currentPage", users.getNumber(),
                            "totalItems", users.getTotalElements(),
                            "totalPages", users.getTotalPages(),
                            "users" , dtos
                    ))
                    .build();
        } catch (Exception e) {
            throw new GeneralException(500, e.getMessage());
        }


//        for(User user: users) {
//            System.out.println(user.getName());
//        }
//
//        Response response = new Response();
//        response .setRc("000");
//        response.setMessage("Success");
//        response.setData(users);


    }

    public GenericResponse createEndUser(CreateEndUserRequestDTO dto) {

        try {
            EndUser endUser = new EndUser();
            endUser.setName(dto.getName());
            endUser.setEmail(dto.getEmail());
            endUser.setPassword(dto.getPassword());
            endUser.setPhoneNumber(dto.getPhoneNumber());
            endUser.setGender(dto.getGender());
            endUser.setUsername(dto.getUsername());
            endUser.setProfilePicture(dto.getProfilePicture());
            endUser.setOccupation(dto.getOccupation());
            endUser.setDateOfBirth(dto.getDateOfBirth());
            System.out.println(endUser);
        } catch (Exception e) {
            throw new GeneralException(500, e.getMessage());
        }

        return GenericResponse.builder()
                .success(true)
                .message("Successfully created End User")
                .code(200)
                .build();
    }

    @Override
    public GenericResponse getUser(Long id) {
        UserDetailResponseDTO userDetail;
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new GeneralException(404, "User not found"));
            EndUser endUser = (EndUser) user;

            userDetail = UserDetailResponseDTO.builder()
                    .username(endUser.getUsername())
                    .dateOfBirth(endUser.getDateOfBirth())
                    .gender(endUser.getGender())
                    .occupation(endUser.getOccupation())
                    .name(endUser.getName())
                    .username(endUser.getUsername())
                    .phoneNumber(endUser.getPhoneNumber())
                    .email(endUser.getEmail())
                    .password(endUser.getPassword())
                    .profilePicture(endUser.getProfilePicture())
                    .build();
        } catch (Exception e) {
            throw new GeneralException(500, e.getMessage());
        }

        return GenericResponse.builder()
                .code(200)
                .success(true)
                .data(userDetail)
                .build();
    }

    @Override
    public GenericResponse deleteUser(Long id) {
        try {
            int affected = userRepository.softDeleteById(id);
            if(affected == 0) {
                throw new GeneralException(404, "User not found or already deleted");
            }
        } catch (Exception e) {
            System.out.println("lock here");
            throw new GeneralException(500, e.getMessage());
        }
        return GenericResponse.builder()
                .success(true)
                .code(200)
                .message("The data is deleted successfully")
                .build();
    }

    public GenericResponse getTotalUsers() {
        Long totalUsers = 0L;
        Map<String, Long> result = new HashMap<>();

        try {
            totalUsers = userRepository.count();
            result.put("totalUsers", totalUsers);
        } catch (Exception e) {
            throw new GeneralException(500, e.getMessage());
        }
        return GenericResponse.builder()
                .success(true)
                .code(200)
                .data(result)
                .build();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
