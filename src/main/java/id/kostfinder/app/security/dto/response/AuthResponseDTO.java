package id.kostfinder.app.security.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {
    private String message; // Pesan sukses atau error
    private String username; // Username atau email pengguna yang berhasil login
    private String role; // Peran pengguna
}
