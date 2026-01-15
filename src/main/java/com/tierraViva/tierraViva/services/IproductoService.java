package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IproductoService {

    List<Producto> obtenerTodos();
    Optional<Producto> obtenerPorId(Long id);
    Producto crearProducto(Producto producto);
    void eliminarProducto(Long id);

    Producto agregarCategoria(Long productoId, Long categoriaId);
}
