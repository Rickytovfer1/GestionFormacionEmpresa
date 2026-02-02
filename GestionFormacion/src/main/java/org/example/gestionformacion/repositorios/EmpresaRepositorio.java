package org.example.gestionformacion.repositorios;

import org.example.gestionformacion.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepositorio extends JpaRepository<Empresa, Integer> {
}
