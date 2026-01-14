package com.tierraViva.tierraViva.repositories;

import com.tierraViva.tierraViva.models.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}
