package com.tierraViva.tierraViva.repositories;

import com.tierraViva.tierraViva.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
}
