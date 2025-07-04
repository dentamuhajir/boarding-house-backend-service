package id.kostfinder.app.security.controller;

import id.kostfinder.app.response.GenericResponse;
import id.kostfinder.app.security.dto.request.LoginRequestDTO;
import id.kostfinder.app.security.jwt.JwtUtil;
import id.kostfinder.app.security.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtil jwtUtil;
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse responseHttp) {
        GenericResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-jwt")
    public ResponseEntity<?> checkJwtFromCookie(HttpServletRequest request) {
        System.out.println("hit ");
//
//        System.out.println(request.getCookies());
//        System.out.println("pass");
        if (request.getCookies() != null) {
            System.out.println("pass in loop");
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    return ResponseEntity.ok("JWT found: " + token);
                }
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT not found in cookies");
    }

//    @PostMapping("/login")
//    public ResponseEntity<GenericResponse> login(@Valid @RequestBody LoginRequestDTO loginRequest, HttpServletRequest request) {
//        try {
//            // Membuat objek UsernamePasswordAuthenticationToken dari kredensial yang diberikan
//            UsernamePasswordAuthenticationToken authToken =
//                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
//
//            // Melakukan autentikasi menggunakan AuthenticationManager.
//            // Jika autentikasi berhasil, Spring Security akan secara otomatis
//            // mengisi SecurityContextHolder dengan objek Authentication dan membuat sesi HTTP.
//            Authentication authentication = authenticationManager.authenticate(authToken);
//
//            // Set objek Authentication ke SecurityContextHolder
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            // Dapatkan sesi HTTP. `true` akan membuat sesi baru jika belum ada.
//            HttpSession session = request.getSession(true);
//            // Simpan SecurityContext ke sesi HTTP secara eksplisit (opsional, tapi disarankan untuk kejelasan)
//            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
//
//            // Mengambil detail pengguna yang berhasil login
//            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//            // Membangun respon sukses login
//            AuthResponseDTO authResponse = AuthResponseDTO.builder()
//                    .message("Login berhasil!")
//                    .username(userDetails.getUsername())
//                    .role(userDetails.getAuthorities().iterator().next().getAuthority()) // Ambil peran pertama
//                    .build();
//
//            // Mengembalikan respon sukses dengan status OK (200)
//            return ResponseEntity.ok(
//                    GenericResponse.builder()
//                            .success(true)
//                            .message("Login berhasil!")
//                            .code(200)
//                            .data(authResponse)
//                            .build()
//            );
//
//        } catch (AuthenticationException e) {
//            // Tangani kegagalan autentikasi (misalnya, kredensial tidak valid)
//            System.err.println("Autentikasi gagal: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                    GenericResponse.builder()
//                            .success(false)
//                            .message("Email atau password salah. Silakan coba lagi.")
//                            .code(401)
//                            .data(Map.of("error", e.getMessage())) // Informasi error untuk debugging
//                            .build()
//            );
//        } catch (Exception e) {
//            // Tangani error umum lainnya selama proses login
//            System.err.println("Terjadi kesalahan saat login: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                    GenericResponse.builder()
//                            .success(false)
//                            .message("Terjadi kesalahan internal server.")
//                            .code(500)
//                            .data(Map.of("error", e.getMessage())) // Informasi error untuk debugging
//                            .build()
//            );
//        }
//
//    }

    // Endpoint untuk proses logout
    @PostMapping("/logout")
    public ResponseEntity<GenericResponse> logout(HttpServletRequest request) {
        System.out.println("Hit there");
        // Mendapatkan sesi HTTP yang ada
        HttpSession session = request.getSession(false); // false agar tidak membuat sesi baru jika tidak ada

        if (session != null) {
            session.invalidate(); // Invalidasi (hapus) sesi
        }

        // Hapus Authentication dari SecurityContextHolder
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok(
                GenericResponse.builder()
                        .success(true)
                        .message("Logout berhasil!")
                        .code(200)
                        .build()
        );
    }


}
