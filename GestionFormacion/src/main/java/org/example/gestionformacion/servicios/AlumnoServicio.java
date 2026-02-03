package org.example.gestionformacion.servicios;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.repositorios.AlumnoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    public Alumno findById(Integer idAlumno){

        return alumnoRepositorio.findById(idAlumno)
                .orElseThrow(()-> new RuntimeException("Ningun alumno con este ID."));
    }

    public List<Alumno> listarAlumnos(){
        return alumnoRepositorio.findAll();
    }

    public void crearAlumno(Alumno alumno){

        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(alumno.getNombre());
        nuevoAlumno.setApellidos(alumno.getApellidos());
        nuevoAlumno.setEmail(alumno.getEmail());
        nuevoAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
        nuevoAlumno.setCurso(alumno.getCurso());

        alumnoRepositorio.save(nuevoAlumno);

    }

    public void eliminarAlumno(Integer idAlumno){
        alumnoRepositorio.deleteById(idAlumno);
    }

    public void editarAlumno(Alumno alumno, Integer id){

        Alumno nuevoAlumno = alumnoRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Ninguna empresa con este ID."));

        nuevoAlumno.setNombre(alumno.getNombre());
        nuevoAlumno.setApellidos(alumno.getApellidos());
        nuevoAlumno.setEmail(alumno.getEmail());
        nuevoAlumno.setFechaNacimiento(alumno.getFechaNacimiento());
        nuevoAlumno.setCurso(alumno.getCurso());


        alumnoRepositorio.save(nuevoAlumno);

    }
}
