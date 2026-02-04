package org.example.gestionformacion.servicios;

import org.example.gestionformacion.dtos.AlumnoDTO;
import org.example.gestionformacion.dtos.CrearAlumnoDTO;
import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.repositorios.AlumnoRepositorio;
import org.example.gestionformacion.repositorios.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlumnoServicio {

    @Autowired
    private AlumnoRepositorio alumnoRepositorio;

    @Autowired
    private CursoRepositorio cursoRepositorio;

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

    public List<Alumno> buscarIdCurso(Integer idCurso) {
        return alumnoRepositorio.findByCurso_Id(idCurso);
    }


    public AlumnoDTO findByIdRest(Integer idAlumno){
        Alumno alumno = alumnoRepositorio.findById(idAlumno)
                .orElseThrow(()-> new RuntimeException("Ningun alumno con este ID."));
        return getAlumnoDTO(alumno);
    }

    public void crearAlumnoRest(CrearAlumnoDTO dto){

        Alumno nuevoAlumno = new Alumno();
        nuevoAlumno.setNombre(dto.getNombre());
        nuevoAlumno.setApellidos(dto.getApellidos());
        nuevoAlumno.setEmail(dto.getEmail());
        nuevoAlumno.setFechaNacimiento(dto.getFechaNacimiento());

        Curso curso = cursoRepositorio.findById(dto.getIdCurso())
                .orElseThrow(()-> new RuntimeException("Ningun curso con este ID."));

        nuevoAlumno.setCurso(curso);

        alumnoRepositorio.save(nuevoAlumno);

    }

    public List<AlumnoDTO> listarAlumnosRest(){
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        List<AlumnoDTO> dtos = new ArrayList<>();

        for (Alumno alumno: alumnos){
            dtos.add(getAlumnoDTO(alumno));
        }

        return dtos;
    }

    public void editarAlumnoRest(CrearAlumnoDTO dto, Integer idAlumno){

        Alumno nuevoAlumno = alumnoRepositorio.findById(idAlumno)
                .orElseThrow(()-> new RuntimeException("Ningun alumno con este ID."));

        nuevoAlumno.setNombre(dto.getNombre());
        nuevoAlumno.setApellidos(dto.getApellidos());
        nuevoAlumno.setEmail(dto.getEmail());
        nuevoAlumno.setFechaNacimiento(dto.getFechaNacimiento());

        Curso curso = cursoRepositorio.findById(dto.getIdCurso())
                .orElseThrow(()-> new RuntimeException("Ningun curso con este ID."));

        nuevoAlumno.setCurso(curso);

        alumnoRepositorio.save(nuevoAlumno);

    }

    public static AlumnoDTO getAlumnoDTO(Alumno a) {
        AlumnoDTO dto = new AlumnoDTO();
        dto.setId(a.getId());
        dto.setNombre(a.getNombre());
        dto.setApellidos(a.getApellidos());
        dto.setEmail(a.getEmail());
        dto.setFechaNacimiento(a.getFechaNacimiento());

        if (a.getCurso() != null) {
            dto.setIdCurso(a.getCurso().getId());
        }

        return dto;
    }


}
