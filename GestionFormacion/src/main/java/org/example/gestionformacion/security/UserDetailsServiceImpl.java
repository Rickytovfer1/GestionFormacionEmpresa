package org.example.gestionformacion.security;

import org.example.gestionformacion.modelos.Profesor;
import org.example.gestionformacion.repositorios.ProfesorRepositorio;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ProfesorRepositorio profesorRepositorio;

    public UserDetailsServiceImpl(ProfesorRepositorio profesorRepositorio) {
        this.profesorRepositorio = profesorRepositorio;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Profesor profesor = profesorRepositorio.findByEmail(email);

        if (profesor == null) {
            throw new UsernameNotFoundException("Profesor no encontrado: " + email);
        }

        return User.withUsername(profesor.getNombre())
                .password(profesor.getContrasena())
                .roles(profesor.getRol().toString())
                .build();
    }
}
