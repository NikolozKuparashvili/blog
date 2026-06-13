package com.example.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/style.css", "/actuator/health").permitAll()
                        .requestMatchers("/create").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("Joe Rogan")
                .password(passwordEncoder().encode("UFC 250"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}