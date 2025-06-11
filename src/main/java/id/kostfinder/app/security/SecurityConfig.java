package id.kostfinder.app.security;

import id.kostfinder.app.user.model.EndUser;
import id.kostfinder.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserService userService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // In REST we are no need csrf
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/register", "/api/login").permitAll() // by default if we activated spring security that means all enpoint is protected so w permitted login and register
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin().disable(); // We use REST not FORM so we disabled it

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            // So User Facade already use by UserDetailService
            id.kostfinder.app.user.model.User user = userService.findByEmail(email); // Cast to EndUser
            if (user == null) throw new UsernameNotFoundException("Email not found");

            return User.withUsername(user.getEmail()) // withUsername is a static factory method provided by Spring Security’s
                    .password(user.getPassword())
                    .roles("USER")
                    .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
