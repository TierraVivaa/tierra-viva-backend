package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Pago;

public interface IpagoService {

    Pago obtenerPorId(Long id);
    Pago crearPago(Pago pago);
    void eliminarPago(Long id);

    Pago asignarCarrito(Long idPago, Long idCarrito);
}
