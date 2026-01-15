package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.services.IcategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    public CategoriaController(IcategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    private final IcategoriaService categoriaService;

    // 1. Obtener todas las categorías
    @GetMapping
    public List<Categoria> listar() {
        return categoriaService.obtenerTodos();
    }

    // 2. Obtener una categoría por ID (Manejando el Optional)
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtenerPorId(@PathVariable Long id) {
        return categoriaService.obtenerPorId(id)
                .map(ResponseEntity::ok) // Si existe, devuelve 200 OK con la categoría
                .orElse(ResponseEntity.notFound().build()); // Si no, devuelve 404 Not Found
    }

    // 3. Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        Categoria nuevaCategoria = categoriaService.crearCategoria(categoria);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    // 4. Eliminar una categoría
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return ResponseEntity.noContent().build(); // Devuelve 204 No Content si se borra con éxito
    }
}