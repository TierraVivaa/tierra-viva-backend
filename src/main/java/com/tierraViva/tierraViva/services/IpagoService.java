package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Pago;

import java.util.Optional;

public interface IpagoService {

    Optional<Pago> obtenerPorId(Long id);
    Pago crearPago(Pago pago);
    void eliminarPago(Long id);

    Pago asignarCarrito(Long idPago, Long idCarrito);
}
