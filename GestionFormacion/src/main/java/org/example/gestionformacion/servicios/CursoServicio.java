package org.example.gestionformacion.servicios;

import org.example.gestionformacion.modelos.Curso;
import org.example.gestionformacion.repositorios.CursoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServicio {

    @Autowired
    private CursoRepositorio cursoRepositorio;

    public Curso findById(Integer idCurso){
        Curso curso = cursoRepositorio.findById(idCurso)
                .orElseThrow(()-> new RuntimeException("Ningun curso con este ID."));
        return  curso;
    }

    public void crearCurso(Curso curso){
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(curso.getNombre());
        cursoRepositorio.save(nuevoCurso);
    }

    public void editarCurso(Curso curso, Integer idCurso){
        Curso editarCurso = cursoRepositorio.findById(idCurso)
                .orElseThrow(()-> new RuntimeException("Ningun curso con este ID."));
        editarCurso.setNombre(curso.getNombre());
        cursoRepositorio.save(editarCurso);
    }

    public List<Curso> listarCursos(){
        List<Curso> listaCursos = cursoRepositorio.findAll();
        return listaCursos;
    }

    public void eliminarCurso(Integer idCurso){
        cursoRepositorio.deleteById(idCurso);
    }

}
