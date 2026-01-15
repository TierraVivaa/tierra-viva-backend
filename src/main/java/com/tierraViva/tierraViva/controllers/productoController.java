package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.services.IproductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class productoController {

    private final IproductoService productoService;

    public productoController(IproductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public List<Producto> obtenerTodos() {
        return  productoService.obtenerTodos();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerTodos(@PathVariable Long id) {
        return  productoService.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );
    }

    @PostMapping("/productos")
    public Producto crearProducto(@RequestBody Producto producto) {
        System.out.println("WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW");
        System.out.println(producto);
        return productoService.crearProducto(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PostMapping("/productos/{productoId}/{categoriaId}")
    public Producto agregarCategoria(@PathVariable Long productoId, @PathVariable Long categoriaId) {
        return productoService.agregarCategoria(productoId, categoriaId);
    }

}
