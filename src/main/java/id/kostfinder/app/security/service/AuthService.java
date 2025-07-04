package id.kostfinder.app.security.service;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.security.dto.request.LoginRequestDTO;

public interface AuthService {
    GenericResponse login(LoginRequestDTO loginRequest);
}
