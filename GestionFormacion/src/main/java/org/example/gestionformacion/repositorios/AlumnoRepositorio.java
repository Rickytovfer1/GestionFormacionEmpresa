package org.example.gestionformacion.repositorios;

import org.example.gestionformacion.modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Integer> {
    List<Alumno> findByCurso_Id(Integer idCurso);
}
