package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Pago;
import com.tierraViva.tierraViva.services.IpagoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PagoController {
    private final IpagoService pagoService;

    public PagoController(IpagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping("/pagos/{id}")
    public Pago obtenerPorId(@PathVariable Long id) {
        return  pagoService.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Pago no encontrado")
        );
    }

    @PostMapping("/pagos")
    public Pago crearPago(@RequestBody Pago pago) {

        /*{
            "metodoPago": "efectivo",
            "fechaPago": "2020-12-31",
            "monto":"5390.01"
        }*/

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
}
