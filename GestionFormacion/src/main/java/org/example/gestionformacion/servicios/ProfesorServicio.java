package org.example.gestionformacion.servicios;

import lombok.AllArgsConstructor;
import org.example.gestionformacion.modelos.Profesor;
import org.example.gestionformacion.repositorios.ProfesorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.gestionformacion.enumerados.Rol;

import java.util.List;

@Service
public class ProfesorServicio {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    public Profesor findById(Integer idProfesor){
        Profesor profesor = profesorRepositorio.findById(idProfesor)
                .orElseThrow(()-> new RuntimeException("Ningun profesor con este ID."));

        return profesor;
    }

    public void crearProfesor(Profesor profesor, Rol rol) {

        Profesor profesorNuevo = new Profesor();
        profesorNuevo.setNombre(profesor.getNombre());
        profesorNuevo.setApellidos(profesor.getApellidos());
        profesorNuevo.setEmail(profesor.getEmail());

        profesorNuevo.setContrasena(profesor.getContrasena());

        profesorNuevo.setRol(rol);

        profesorRepositorio.save(profesorNuevo);
    }


    public List<Profesor> listarProfesores(){
        List<Profesor> listaProfesores = profesorRepositorio.findAll();
        return listaProfesores;
    }

    public void editarProfesor(Integer idProfesor, Profesor profesorFormulario, Rol rol) {

        Profesor profesorEditar = profesorRepositorio.findById(idProfesor)
                .orElseThrow(() -> new RuntimeException("No existe un profesor con ese ID"));

        profesorEditar.setNombre(profesorFormulario.getNombre());
        profesorEditar.setApellidos(profesorFormulario.getApellidos());
        profesorEditar.setEmail(profesorFormulario.getEmail());

        if (profesorFormulario.getContrasena() != null &&
                !profesorFormulario.getContrasena().isBlank()) {

            profesorEditar.setContrasena(profesorFormulario.getContrasena());
        }

        profesorEditar.setRol(rol);

        profesorRepositorio.save(profesorEditar);
    }

    public void eliminarProfesor(Integer id){
        profesorRepositorio.deleteById(id);
    }


}
