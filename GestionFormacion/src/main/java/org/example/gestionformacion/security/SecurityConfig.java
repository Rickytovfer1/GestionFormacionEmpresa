package org.example.gestionformacion.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        http.authorizeHttpRequests(auth -> auth

                .requestMatchers("/practica/**").hasAnyRole("DIRECTIVO", "NORMAL")
                .requestMatchers("/alumno/**").hasAnyRole("DIRECTIVO", "NORMAL")
                .requestMatchers("/empresa/**").hasAnyRole("DIRECTIVO", "NORMAL")

                .requestMatchers("/profesor/**").hasRole("DIRECTIVO")
                .requestMatchers("/curso/**").hasRole("DIRECTIVO")

                .anyRequest().authenticated()
        );

        http.formLogin(form -> form
                .loginProcessingUrl("/api/auth/login")
                .defaultSuccessUrl("/alumno/listaAlumnos", true)
                .permitAll()
        );

        http.logout(logout -> logout.permitAll());

        return http.build();
    }

}
