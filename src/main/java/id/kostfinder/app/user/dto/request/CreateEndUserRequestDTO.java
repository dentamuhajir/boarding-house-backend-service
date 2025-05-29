package id.kostfinder.app.user.dto.request;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CreateEndUserRequestDTO {
    private String name;
    private String email;
    private String password;
    private String username;
    private String profilePicture;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private String occupation;
}
