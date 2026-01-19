package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;

import java.util.Optional;

public interface IcarritoService {

    Optional<Carrito> obtenerPorId(Long id);
    Carrito crearCarrito(Carrito carrito);
    void eliminarCarrito(Long id);

    Carrito asignarUsuario(Long idUsurio, Long idCarrito);
}
