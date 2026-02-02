package org.example.gestionformacion.repositorios;

import org.example.gestionformacion.modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepositorio extends JpaRepository<Profesor, Integer> {
}
