package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.services.IcarritoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CarritoController {
    private final IcarritoService carritoService;

    public CarritoController(IcarritoService carritoService) {
        this.carritoService = carritoService;
    }

    @GetMapping("/carritos/{id}")
    public Carrito obtenerPorId(@PathVariable Long id) {
        return  carritoService.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );
    }

    @PostMapping("/carritos")
    public Carrito crearCarrito(@RequestBody Carrito carrito) {

        /*{
            "precioTotal": "123",
            "fechaPedido": "2020-12-31T15:53:16"
        }*/

        return carritoService.crearCarrito(carrito);
    }

    @DeleteMapping("/carritos/{id}")
    public ResponseEntity<String> eliminarCarrito(@PathVariable Long id){
        carritoService.eliminarCarrito(id);
        return ResponseEntity.ok("Carrito eliminado con éxito");
    }

    @PostMapping("/carritos/{idCarrito}/usuarios/{idUsuario}")
    public Carrito agregarUsuario(@PathVariable Long idCarrito, @PathVariable Long idUsurio) {
        return carritoService.asignarUsuario(idCarrito, idUsurio);
    }
}
