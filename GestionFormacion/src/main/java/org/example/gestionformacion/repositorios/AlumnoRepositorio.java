package org.example.gestionformacion.repositorios;

import org.example.gestionformacion.modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositorio extends JpaRepository<Alumno, Integer> {
}
