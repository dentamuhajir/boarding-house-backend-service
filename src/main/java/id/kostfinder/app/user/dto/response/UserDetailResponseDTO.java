package id.kostfinder.app.user.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class UserDetailResponseDTO {
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
