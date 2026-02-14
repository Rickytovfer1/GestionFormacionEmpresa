package org.example.gestionformacion.controladores;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.servicios.AlumnoServicio;
import org.example.gestionformacion.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alumno")
public class AlumnoControlador {

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private CursoServicio cursoServicio;


    @GetMapping("/listaAlumnos")
    public String listarAlumnos(@RequestParam(required = false) Integer idCurso,
                                @RequestParam(defaultValue = "es") String idioma,
                                Model model) {

        if (idCurso != null) {
            model.addAttribute("listaAlumnos",
                    alumnoServicio.buscarIdCurso(idCurso));
        } else {
            model.addAttribute("listaAlumnos",
                    alumnoServicio.listarAlumnos());
        }
        Map<String, String> textos = new HashMap<>();
        if ("en".equals(idioma)) {
            textos.put("titulo", "Students List");
            textos.put("nombre", "First Name");
            textos.put("apellidos", "Last Name");
            textos.put("fechaNacimiento", "Birth Date");
            textos.put("cursos", "All courses");
            textos.put("email", "Email");
            textos.put("curso", "Course");
            textos.put("acciones", "Actions");
            textos.put("editar", "Edit");
            textos.put("eliminar", "Delete");
            textos.put("ver", "View");
            textos.put("crearAlumno", "Create New Student");
            textos.put("filtrar", "Filter");
            textos.put("limpiar", "Clear");
            textos.put("todosCursos", "All Courses");
            textos.put("noAlumnos", "No students available");
            textos.put("importarAlumno", "Import students");

        } else {
            textos.put("titulo", "Lista de Alumnos");
            textos.put("nombre", "Nombre");
            textos.put("apellidos", "Apellidos");
            textos.put("fechaNacimiento", "Fecha de nacimiento");
            textos.put("cursos", "Todos los cursos");
            textos.put("email", "Email");
            textos.put("curso", "Curso");
            textos.put("acciones", "Acciones");
            textos.put("editar", "Editar");
            textos.put("eliminar", "Eliminar");
            textos.put("ver", "Ver");
            textos.put("crearAlumno", "Crear nuevo alumno");
            textos.put("filtrar", "Filtrar");
            textos.put("limpiar", "Limpiar");
            textos.put("todosCursos", "Todos los cursos");
            textos.put("noAlumnos", "No hay alumnos");
            textos.put("importarAlumno", "Importar alumnos");
        }

        model.addAttribute("textos", textos);
        model.addAttribute("idioma", idioma);

        model.addAttribute("cursos", cursoServicio.listarCursos());
        model.addAttribute("cursoSeleccionado", idCurso);

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

    @GetMapping("/importar")
    public String mostrarImportar(Model model){

        model.addAttribute("cursos", cursoServicio.listarCursos());

        return "Alumno/importarAlumnos";
    }

    @PostMapping("/importar")
    public String importarAlumnos(@RequestParam Integer idCurso, @RequestParam("archivo") MultipartFile archivo) {

        alumnoServicio.importarAlumnosCSV(idCurso, archivo);

        return "redirect:/alumno/listaAlumnos";
    }

}
