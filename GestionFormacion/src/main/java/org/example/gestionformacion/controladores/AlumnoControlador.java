package org.example.gestionformacion.controladores;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.servicios.AlumnoServicio;
import org.example.gestionformacion.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/alumno")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private CursoServicio cursoServicio;


    @GetMapping("/listaAlumnos")
    public String listarAlumnos(Model model){
        model.addAttribute("listaAlumnos", alumnoServicio.listarAlumnos());
        return "Alumno/alumnos";
    }

    @GetMapping("/crear")
    public String crearAlumno(Model model){
        Alumno a = new Alumno();
        model.addAttribute("alumno", a);
        model.addAttribute("cursos", cursoServicio.listarCursos());

        return "Alumno/crearAlumno";
    }

    @PostMapping("/crear")
    public String crearAlumno(@ModelAttribute("alumno") Alumno alumno, Model model) {

        alumnoServicio.crearAlumno(alumno);
        model.addAttribute("listaAlumnos", alumnoServicio.listarAlumnos());

        return "Alumno/alumnos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Integer id){
        alumnoServicio.eliminarAlumno(id);
        return "redirect:/alumno/listaAlumnos";
    }

    @GetMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Integer id, Model model){
        Alumno a = alumnoServicio.findById(id);
        model.addAttribute("alumno",a);
        model.addAttribute("cursos", cursoServicio.listarCursos());

        return "Alumno/editarAlumno";
    }

    @PostMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Integer id, @ModelAttribute("alumno") Alumno alumno, Model model) {
        alumnoServicio.editarAlumno(alumno, id);

        model.addAttribute("listaAlumnos", cursoServicio.listarCursos());

        return "redirect:/alumno/listaAlumnos";
    }

    @GetMapping("/ver/{id}")
    public String verAlumno(Model model,@PathVariable Integer id){
        Alumno alumno = alumnoServicio.findById(id);
        model.addAttribute("alumno", alumno);

        return "Alumno/verAlumno";
    }
}
