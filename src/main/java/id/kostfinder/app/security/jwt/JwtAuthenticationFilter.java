package id.kostfinder.app.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component // Menandakan bahwa kelas ini akan dikelola oleh Spring (bisa digunakan otomatis di konfigurasi keamanan)
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // ✅ Constructor injection (Dagger-style)
    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

//    @Autowired
//    private JwtUtil jwtUtil; // Util class untuk memproses token: generate, verifikasi, ekstrak email
//
//    @Autowired
//    private UserDetailsService userDetailsService; // Digunakan untuk load user berdasarkan email dari token

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Ambil header Authorization dari request
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        // Cek apakah header tidak null dan dimulai dengan "Bearer "
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); // Ambil isi token setelah "Bearer "
            email = jwtUtil.extractEmail(token); // Ekstrak email dari token (subject dari JWT)
        }

        // Cek apakah email ditemukan dan belum ada autentikasi aktif di SecurityContext
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Ambil informasi user dari database berdasarkan email
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Validasi apakah token benar dan belum expired
            if (jwtUtil.isTokenValid(token)) {

                // Buat objek autentikasi berdasarkan user yang ditemukan
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, // principal: informasi user
                                null,        // credentials: bisa null karena tidak ada password di sini
                                userDetails.getAuthorities() // role/otorisasi user
                        );

                // Set autentikasi ini ke dalam konteks keamanan Spring
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Teruskan ke filter selanjutnya (atau ke controller jika sudah terakhir)
        filterChain.doFilter(request, response);
    }
}
