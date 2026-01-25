package com.tierraViva.tierraViva;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource())) // ← CORS PRIMERO
                .csrf(csrf -> csrf.disable()) // Desactivamos CSRF para poder usar Postman
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Sin sesiones (JWT)
                .authorizeHttpRequests(auth -> auth
                        // Permitimos el registro de usuarios y el login (cuando lo crees) sin token
                        .requestMatchers("/productos/**").permitAll()
                        .requestMatchers(
                                HttpMethod.GET,
                                "/usuarios/**",
                                "/api/categorias/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST,
                                "/usuarios/**",
                                "/auth/loginConDTO"
                             ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()

                        // Cualquier otra petición requerirá autenticación
                        .anyRequest().authenticated()
                );

        return http.build();
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(Arrays.asList("http://127.0.0.1:*", "http://127.0.0.7:*", "http://localhost:*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    // Este Bean es el que usará tu UsuarioService para encriptar la clave
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Este Bean es necesario para el proceso de Login más adelante
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}