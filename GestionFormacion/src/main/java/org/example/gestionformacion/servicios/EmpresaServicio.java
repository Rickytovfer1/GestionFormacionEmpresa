package org.example.gestionformacion.servicios;

import org.example.gestionformacion.modelos.Empresa;
import org.example.gestionformacion.repositorios.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServicio {

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    public Empresa findById(Integer idEmpresa){

        return empresaRepositorio.findById(idEmpresa)
                .orElseThrow(()-> new RuntimeException("Ninguna empresa con este ID."));
    }

    public List<Empresa> listarEmpresas(){
        return empresaRepositorio.findAll();
    }

    public void crearEmpresa(Empresa empresa){

        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setNombre(empresa.getNombre());
        nuevaEmpresa.setDescripcion(empresa.getDescripcion());
        nuevaEmpresa.setEmailTutor(empresa.getEmailTutor());
        nuevaEmpresa.setNombreTutor(empresa.getNombreTutor());

        empresaRepositorio.save(nuevaEmpresa);

    }

    public void eliminarEmpresa(Integer idEmpresa){
        empresaRepositorio.deleteById(idEmpresa);
    }

    public void editarEmpresa(Empresa empresa, Integer id){

        Empresa nuevaEmpresa = empresaRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Ninguna empresa con este ID."));

        nuevaEmpresa.setNombre(empresa.getNombre());
        nuevaEmpresa.setDescripcion(empresa.getDescripcion());
        nuevaEmpresa.setEmailTutor(empresa.getEmailTutor());
        nuevaEmpresa.setNombreTutor(empresa.getNombreTutor());

        empresaRepositorio.save(nuevaEmpresa);

    }


}
