package id.kostfinder.app.user.dto.response;


import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class UserListResponseDTO {
    private String name;
    private String email;
    private String profilePicture;
    private String phoneNumber;
}
