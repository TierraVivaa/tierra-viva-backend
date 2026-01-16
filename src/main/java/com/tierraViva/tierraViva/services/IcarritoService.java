package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.models.Producto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface IcarritoService {
    // CARRITO
    Carrito crearCarrito(Long idUsuario, BigDecimal precioTotal, LocalDateTime fechaPedido);
    void eliminarCarrito(Long id);
    Optional<Carrito> obtenerPorId(Long id);

    // PAGO
    Pago crearPago(Long idCarrito, String metodoPago, LocalDate fechaPago, BigDecimal monto);
}
