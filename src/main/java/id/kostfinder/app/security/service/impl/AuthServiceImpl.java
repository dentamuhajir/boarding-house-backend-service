package id.kostfinder.app.security.service.impl;

import id.kostfinder.app.security.dto.request.LoginRequestDTO;
import id.kostfinder.app.security.jwt.JwtUtil;
import id.kostfinder.app.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;
    @Override
    public String login(LoginRequestDTO loginRequest) {
        try {
            var authToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
            authenticationManager.authenticate(authToken);

            String jwt = jwtUtil.generateToken(loginRequest.getEmail());

            return jwt;
        } catch (AuthenticationException e) {
            throw new RuntimeException("Email atau password salah");
        }
    }
}
