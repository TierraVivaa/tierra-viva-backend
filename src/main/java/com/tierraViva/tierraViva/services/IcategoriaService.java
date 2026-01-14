package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.models.Producto;

import java.util.List;
import java.util.Optional;

public interface IcategoriaService {

    List<Categoria> obtenerTodos();
    Optional<Categoria> obtenerPorId(Long id);
    Categoria crearCategoria(Categoria categoria);
    void eliminarCategoria(Long id);

}
