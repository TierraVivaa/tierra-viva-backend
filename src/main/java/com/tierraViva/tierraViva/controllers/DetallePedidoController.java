package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.DetallePedido;
import com.tierraViva.tierraViva.services.IdetallePedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalle-pedidos")

public class DetallePedidoController {
    private final IdetallePedidoService detallePedidoService;

    public DetallePedidoController(IdetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }


    @GetMapping
    public List<DetallePedido> obtenerTodos() {
        return detallePedidoService.obtenerTodos();
    }


    @GetMapping("/{id}")
    public DetallePedido obtenerPorId(@PathVariable Long id) {
        return detallePedidoService.obtenerPorId(id).orElse(null);
    }


    @PostMapping
    public DetallePedido crearDetallePedido(
            @RequestParam Long idCarrito,
            @RequestParam Long idProducto,
            @RequestParam int cantidad
    ) {
        return detallePedidoService.crearDetallePedido(idCarrito, idProducto, cantidad);
    }
}

