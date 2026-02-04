package org.example.gestionformacion.servicios;

import org.example.gestionformacion.modelos.Alumno;
import org.example.gestionformacion.modelos.Practica;
import org.example.gestionformacion.repositorios.PracticaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PracticaServicio {

    @Autowired
    private PracticaRepositorio practicaRepositorio;

    public Practica findById(Integer idPractica){

        return practicaRepositorio.findById(idPractica)
                .orElseThrow(()-> new RuntimeException("Ninguna práctica con este ID."));
    }

    public List<Practica> listarPracticas(){
        return practicaRepositorio.findAll();
    }

    public void crearPractica(Practica practica){
        Practica nuevaPractica = new Practica();

        nuevaPractica.setFechaInicio(practica.getFechaInicio());
        nuevaPractica.setFechaFin(practica.getFechaFin());
        nuevaPractica.setComentario(practica.getComentario());
        nuevaPractica.setEmpresa(practica.getEmpresa());
        nuevaPractica.setAlumno(practica.getAlumno());

        practicaRepositorio.save(nuevaPractica);

    }

    public void eliminarPractica(Integer idPractica){
        practicaRepositorio.deleteById(idPractica);
    }

    public void editarPractica(Practica practica, Integer idPractica){
        Practica nuevaPractica = practicaRepositorio.findById(idPractica)
                .orElseThrow(()-> new RuntimeException("Ninguna práctica con este ID."));

        nuevaPractica.setFechaInicio(practica.getFechaInicio());
        nuevaPractica.setFechaFin(practica.getFechaFin());
        nuevaPractica.setComentario(practica.getComentario());
        nuevaPractica.setEmpresa(practica.getEmpresa());
        nuevaPractica.setAlumno(practica.getAlumno());

        practicaRepositorio.save(nuevaPractica);

    }

}
