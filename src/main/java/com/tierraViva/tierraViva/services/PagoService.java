package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.repositories.PagoRepository;

public class PagoService implements IpagoService{
    private final PagoRepository pagoRepository;
    private final IcarritoService carritoService;

    public PagoService(PagoRepository pagoRepository, IcarritoService carritoService) {
        this.pagoRepository = pagoRepository;
        this.carritoService = carritoService;
    }

    @Override
    public Pago obtenerPorId(Long id) {
        return null;
    }

    @Override
    public Pago crearPago(Pago pago) {
        return null;
    }

    @Override
    public void eliminarPago(Long id) {

    }

    @Override
    public Pago asignarCarrito(Long idPago, Long idCarrito) {
        Carrito carrito = carritoService.obtenerPorId(idCarrito).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );

        Pago pago = obtenerPorId(idPago);

        pago.setCarritoPago(carrito);

        return pagoRepository.save(pago);
    }
}
