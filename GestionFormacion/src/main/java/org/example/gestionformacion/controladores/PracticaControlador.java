package org.example.gestionformacion.controladores;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.modelos.Practica;
import org.example.gestionformacion.servicios.AlumnoServicio;
import org.example.gestionformacion.servicios.EmpresaServicio;
import org.example.gestionformacion.servicios.PracticaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/practica")
public class PracticaControlador {

    @Autowired
    private PracticaServicio practicaServicio;

    @Autowired
    private AlumnoServicio alumnoServicio;

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping("/listaPracticas")
    public String listarEmpresas(Model model){
        model.addAttribute("listaPracticas", practicaServicio.listarPracticas());
        return "Practica/practicas";
    }

    @GetMapping("/crear")
    public String crearPractica(Model model){
        Practica p = new Practica();
        model.addAttribute("practica", p);
        model.addAttribute("alumnos", alumnoServicio.listarAlumnos());
        model.addAttribute("empresas", empresaServicio.listarEmpresas());

        return "Practica/crearPractica";
    }

    @PostMapping("/crear")
    public String crearPractica(@ModelAttribute("practica") Practica practica, Model model) {

        practicaServicio.crearPractica(practica);

        model.addAttribute("listaPracticas", practicaServicio.listarPracticas());
        return "Practica/practicas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPractica(@PathVariable Integer id){
        practicaServicio.eliminarPractica(id);
        return "redirect:/practica/listaPracticas";
    }

    @GetMapping("/editar/{id}")
    public String editarPractica(@PathVariable Integer id, Model model){

        Practica p = practicaServicio.findById(id);
        model.addAttribute("practica",p);
        model.addAttribute("alumnos", alumnoServicio.listarAlumnos());
        model.addAttribute("empresas", empresaServicio.listarEmpresas());

        return "Practica/editarPractica";
    }

    @PostMapping("/editar/{id}")
    public String editarAlumno(@PathVariable Integer id, @ModelAttribute("practica") Practica practica, Model model) {
        practicaServicio.editarPractica(practica, id);

        model.addAttribute("listaPracticas", practicaServicio.listarPracticas());

        return "redirect:/practica/listaPracticas";
    }

    @GetMapping("/ver/{id}")
    public String verPractica(Model model,@PathVariable Integer id){
        Practica practica = practicaServicio.findById(id);
        model.addAttribute("practica", practica);

        return "Practica/verPractica";
    }
}
