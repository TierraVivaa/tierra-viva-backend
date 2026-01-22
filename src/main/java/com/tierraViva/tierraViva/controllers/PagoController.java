package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.services.IpagoService;
import com.tierraViva.tierraViva.services.PagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PagoController {

    private final IpagoService pagoService;
    private final PagoService mercadoPagoService;

    public PagoController(IpagoService pagoService, PagoService mercadoPagoService) {
        this.pagoService = pagoService;
        this.mercadoPagoService = mercadoPagoService;
    }

    // =========================
    // ====== ENDPOINTS VIEJOS ==
    // =========================

    @GetMapping("/pagos/{id}")
    public Pago obtenerPorId(@PathVariable Long id) {
        return pagoService.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado")
        );
    }

    @PostMapping("/pagos")
    public Pago crearPago(@RequestBody Pago pago) {
        return pagoService.crearPago(pago);
    }

    @DeleteMapping("/pagos/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Long id){
        pagoService.eliminarPago(id);
        return ResponseEntity.ok("Pago eliminado con éxito");
    }

    @PostMapping("/pagos/{idPago}/carritos/{idCarrito}")
    public Pago agregarCarrito(@PathVariable Long idPago, @PathVariable Long idCarrito) {
        return pagoService.asignarCarrito(idPago, idCarrito);
    }

    // ====================================
    // ====== NUEVO: MERCADO PAGO =========
    // ====================================

    /**
     * Crea un pago en Mercado Pago a partir de un carrito
     * Retorna la URL del checkout
     */
    @GetMapping("/pagos/mercadopago/{idCarrito}")
    public Map<String, String> pagarConMercadoPago(@PathVariable Long idCarrito) {
        String initPoint = mercadoPagoService.crearPagoConMercadoPago(idCarrito);
        return Map.of("init_point", initPoint);
    }
}
