package com.tierraViva.tierraViva.repositories;

import com.tierraViva.tierraViva.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
