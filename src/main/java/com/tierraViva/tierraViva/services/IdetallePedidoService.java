package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.models.DetallePedido;
import com.tierraViva.tierraViva.models.Pago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IdetallePedidoService {

    // CARRITO
    Carrito crearCarrito(Long idUsuario, BigDecimal precioTotal, LocalDateTime fechaPedido);
    void eliminarCarrito(Long id);

    // PAGO
    Pago crearPago(Long idCarrito, String metodoPago, LocalDate fechaPago, BigDecimal monto);

    // DETALLE PEDIDO
    List<DetallePedido> obtenerTodos();
    Optional<DetallePedido> obtenerPorId(Long id);
    DetallePedido crearDetallePedido(Long idCarrito, Long idProducto, int cantidad);
}
