package org.example.gestionformacion.controladores;

import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.modelos.Profesor;
import org.example.gestionformacion.servicios.EmpresaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/empresa")
public class EmpresaControlador {

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping("/listaEmpresas")
    public String listarEmpresas(Model model){
        model.addAttribute("listaEmpresas", empresaServicio.listarEmpresas());
        return "Empresa/empresas";
    }

    @GetMapping("/crear")
    public String crearEmpresa(Model model){
        Empresa e = new Empresa();
        model.addAttribute("empresa", e);

        return "Empresa/crearEmpresa";
    }

    @PostMapping("/crear")
    public String crearEmpresa(@ModelAttribute("empresa") Empresa empresa, Model model) {

        empresaServicio.crearEmpresa(empresa);

        model.addAttribute("listaEmpresas", empresaServicio.listarEmpresas());
        return "Empresa/empresas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarEmpresa(@PathVariable Integer id){
        empresaServicio.eliminarEmpresa(id);
        return "redirect:/empresa/listaEmpresas";
    }

    @GetMapping("/editar/{id}")
    public String editarEmpresa(@PathVariable Integer id, Model model){
        Empresa e = empresaServicio.findById(id);
        model.addAttribute("empresa",e);

        return "Empresa/editarEmpresa";
    }

    @PostMapping("/editar/{id}")
    public String editarEmpresa(@PathVariable Integer id, @ModelAttribute("empresa") Empresa empresa, Model model) {
        empresaServicio.editarEmpresa(empresa, id);
        model.addAttribute("listaEmpresas", empresaServicio.listarEmpresas());

        return "redirect:/empresa/listaEmpresas";
    }

    @GetMapping("/ver/{id}")
    public String verEmpresa(Model model,@PathVariable Integer id){
        Empresa empresa = empresaServicio.findById(id);
        model.addAttribute("empresa",empresa);

        return "Empresa/verEmpresa";
    }
}
