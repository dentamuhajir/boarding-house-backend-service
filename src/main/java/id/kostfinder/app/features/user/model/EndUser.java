package id.kostfinder.app.features.user.model;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@DiscriminatorValue("END_USER")
public class EndUser extends User {
    private String username;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String gender;
    private String occupation;

}
