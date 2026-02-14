package org.example.gestionformacion.servicios;

import lombok.AllArgsConstructor;
import org.example.gestionformacion.modelos.Profesor;
import org.example.gestionformacion.repositorios.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.gestionformacion.enumerados.Rol;

import java.util.List;

@Service
public class ProfesorServicio {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Profesor findById(Integer idProfesor){

        return profesorRepositorio.findById(idProfesor)
                .orElseThrow(()-> new RuntimeException("Ningun profesor con este ID."));
    }

    public void crearProfesor(Profesor profesor, Rol rol) {

        Profesor profesorNuevo = new Profesor();
        profesorNuevo.setNombre(profesor.getNombre());
        profesorNuevo.setApellidos(profesor.getApellidos());
        profesorNuevo.setEmail(profesor.getEmail());

        profesorNuevo.setContrasena(passwordEncoder.encode(profesor.getContrasena()));

        profesorNuevo.setRol(rol);

        profesorRepositorio.save(profesorNuevo);
    }


    public List<Profesor> listarProfesores(){
        return profesorRepositorio.findAll();
    }

    public void editarProfesor(Integer idProfesor, Profesor profesorFormulario, Rol rol) {

        Profesor profesorEditar = profesorRepositorio.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("No existe un profesor con ese ID"));

        profesorEditar.setNombre(profesorFormulario.getNombre());
        profesorEditar.setApellidos(profesorFormulario.getApellidos());
        profesorEditar.setEmail(profesorFormulario.getEmail());

        if (profesorFormulario.getContrasena() != null &&
                !profesorFormulario.getContrasena().isBlank()) {

            profesorEditar.setContrasena(passwordEncoder.encode(profesorFormulario.getContrasena()));
        }

        profesorEditar.setRol(rol);

        profesorRepositorio.save(profesorEditar);
    }

    public void eliminarProfesor(Integer id){
        profesorRepositorio.deleteById(id);
    }


}
