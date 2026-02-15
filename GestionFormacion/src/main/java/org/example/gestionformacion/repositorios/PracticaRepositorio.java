package org.example.gestionformacion.repositorios;

import org.example.gestionformacion.modelos.Practica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticaRepositorio extends JpaRepository<Practica, Integer> {
    boolean existsByAlumno_Id(Integer idAlumno);
}
