package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.repositories.PagoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PagoService implements IpagoService{
    private final PagoRepository pagoRepository;
    private final IcarritoService carritoService;

    public PagoService(PagoRepository pagoRepository, IcarritoService carritoService) {
        this.pagoRepository = pagoRepository;
        this.carritoService = carritoService;
    }

    @Override
    public Optional<Pago> obtenerPorId(Long id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago crearPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPago(Long id) {
        pagoRepository.delete(pagoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado")
        ));
    }

    @Override
    public Pago asignarCarrito(Long idPago, Long idCarrito) {
        Carrito carrito = carritoService.obtenerPorId(idCarrito).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );

        Pago pago = obtenerPorId(idPago).orElseThrow(
                () -> new RuntimeException("Pago no encontrado")
        );

        pago.setCarritoPago(carrito);

        return pagoRepository.save(pago);
    }
}
