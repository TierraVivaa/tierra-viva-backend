package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.models.Usuario;
import com.tierraViva.tierraViva.repositories.CarritoRepository;
import com.tierraViva.tierraViva.repositories.PagoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CarritoService implements IcarritoService {

    private final CarritoRepository carritoRepository;
    private final IusuarioService usuarioService;
    private final PagoRepository pagoRepository;

    public CarritoService(CarritoRepository carritoRepository, IusuarioService usuarioService, PagoRepository pagoRepository) {
        this.carritoRepository = carritoRepository;
        this.usuarioService = usuarioService;
        this.pagoRepository = pagoRepository;
    }

    @Override
    public Carrito crearCarrito(Long idUsuario, BigDecimal precioTotal, LocalDateTime fechaPedido) {
        Usuario usuario = usuarioService.obtenerPorId(idUsuario).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );

        Carrito carrito = new Carrito();
        carrito.setUsuarioCarrito(usuario);
        carrito.setPrecioTotal(precioTotal);
        carrito.setFechaPedido(fechaPedido);

        return carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Pago crearPago(Long idCarrito, String metodoPago, LocalDate fechaPago, BigDecimal monto) {
        Carrito carrito = obtenerPorId(idCarrito).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );

        Pago pago = new Pago();
        pago.setCarritoPago(carrito);
        pago.setFechaPago(fechaPago);
        pago.setMetodoPago(metodoPago);
        pago.setMonto(monto);

        return pagoRepository.save(pago);
    }
}
