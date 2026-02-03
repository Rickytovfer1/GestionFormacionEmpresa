package org.example.gestionformacion.controladores;

import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.servicios.CursoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/curso")
public class CursoControlador {

    @Autowired
    private CursoServicio cursoServicio;

    @GetMapping("/listaCursos")
    public String listarCursos(Model model){
        model.addAttribute("listaCurso", cursoServicio.listarCursos());
        return "Curso/cursos";
    }

    @GetMapping("/crear")
    public String crearCurso(Model model){
        Curso c = new Curso();
        model.addAttribute("curso", c);

        return "Curso/crearCurso";
    }

    @PostMapping("/crear")
    public String crearCurso(@ModelAttribute("curso") Curso curso, Model model) {


        cursoServicio.crearCurso(curso);

        model.addAttribute("listaCurso", cursoServicio.listarCursos());
        return "Curso/cursos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Integer id){
        cursoServicio.eliminarCurso(id);
        return "redirect:/curso/listaCursos";
    }

    @GetMapping("/editar/{id}")
    public String editarCurso(@PathVariable Integer id, Model model){
        Curso c = cursoServicio.findById(id);
        model.addAttribute("curso",c);

        return "Curso/editarCurso";
    }

    @PostMapping("/editar/{id}")
    public String editarCurso(@PathVariable Integer id, @ModelAttribute("curso") Curso curso, Model model) {
        cursoServicio.editarCurso(curso, id);
        model.addAttribute("listaCurso", cursoServicio.listarCursos());

        return "redirect:/curso/listaCursos";
    }


    @GetMapping("/ver/{id}")
    public String verCurso(Model model,@PathVariable Integer id){
        Curso curso = cursoServicio.findById(id);
        model.addAttribute("curso",curso);

        return "Curso/verCurso";
    }

}
