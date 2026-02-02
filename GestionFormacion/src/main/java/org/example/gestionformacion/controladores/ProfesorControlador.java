package org.example.gestionformacion.controladores;

import lombok.AllArgsConstructor;
import org.example.gestionformacion.enumerados.Rol;
import org.example.gestionformacion.modelos.Profesor;
import org.example.gestionformacion.servicios.ProfesorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profesor")
public class ProfesorControlador {

    @Autowired
    private ProfesorServicio profesorServicio;

    @GetMapping("/listaProfesores")
    public String listarProfesores(Model model){
        model.addAttribute("listaProfesores", profesorServicio.listarProfesores());
        return "profesores";
    }

    @GetMapping("/crear")
    public String crearProfesor(Model model){
        Profesor p = new Profesor();
        model.addAttribute("profesor",p);

        return "crearProfesor";
    }

    @PostMapping("/crear")
    public String crearProfesor(@ModelAttribute("profesor") Profesor profesor,
                                @RequestParam(required = false) Boolean directiva, Model model) {

        Rol rol = Boolean.TRUE.equals(directiva) ? Rol.DIRECTIVO : Rol.NORMAL;

        profesorServicio.crearProfesor(profesor, rol);

        model.addAttribute("listaProfesores", profesorServicio.listarProfesores());
        return "profesores";
    }
}
