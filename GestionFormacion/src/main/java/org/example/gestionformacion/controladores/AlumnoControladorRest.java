package org.example.gestionformacion.controladores;

import org.example.gestionformacion.dtos.AlumnoDTO;
import org.example.gestionformacion.dtos.CrearAlumnoDTO;
import org.example.gestionformacion.servicios.AlumnoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnorest")
public class AlumnoControladorRest {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping("/listar/alumnos")
    public List<AlumnoDTO> listarAlumnosRest(){
        return alumnoServicio.listarAlumnosRest();
    }

    @PostMapping("/crear/alumno")
    public void crearAlumnoRest(@RequestBody CrearAlumnoDTO crearAlumnoDTO) {
        alumnoServicio.crearAlumnoRest(crearAlumnoDTO);
    }

    @PostMapping("/eliminar/alumno/{idAlumno}")
    public void eliminarAlumnoRest(@PathVariable Integer idAlumno) {
        alumnoServicio.eliminarAlumno(idAlumno);
    }

    @GetMapping("/ver/alumno/{idAlumno}")
    public AlumnoDTO verEleccionID(@PathVariable Integer idAlumno){
        return alumnoServicio.findByIdRest(idAlumno);
    }

    @PutMapping("/editar/alumno/{idAlumno}")
    public void crearEditarRest(@RequestBody CrearAlumnoDTO dto, @PathVariable Integer idAlumno) {
        alumnoServicio.editarAlumnoRest(dto, idAlumno);
    }
}
