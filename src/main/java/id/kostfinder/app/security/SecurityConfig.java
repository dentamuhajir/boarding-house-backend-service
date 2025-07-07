package id.kostfinder.app.security;

import id.kostfinder.app.security.jwt.JwtAuthenticationFilter;
import id.kostfinder.app.features.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Autowired
//    UserService userService;

    private final UserService userService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    // ✅ Dagger-style injection di constructor
    public SecurityConfig(UserService userService,
                          JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.userService = userService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable() // Untuk REST API, tidak butuh CSRF
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/register",
                                "/api/login",
                                "/api/refresh",
                                "/api/check-jwt",
                                "/users/generated/end-user"
                        ).permitAll() // 🆓 Bebas akses
                        .anyRequest().authenticated() // 🔐 Endpoint lain harus pakai token
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // ❌ Tidak pakai session
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // 🔁 Tambahkan filter JWT
                .formLogin().disable(); // 🔒 Disable form login

        return http.build();
    }


    // Bean untuk PasswordEncoder, digunakan untuk mengenkripsi password.
    // BCryptPasswordEncoder adalah implementasi yang direkomendasikan dan aman.
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    // Bean untuk AuthenticationManager. Ini adalah antarmuka inti untuk melakukan autentikasi.
    // Kita membuat ProviderManager dengan DaoAuthenticationProvider yang menggunakan UserDetailsService
    // dan PasswordEncoder yang kita definisikan.
    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
    

}
