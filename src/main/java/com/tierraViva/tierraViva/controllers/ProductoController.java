package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.services.IcategoriaService;
import com.tierraViva.tierraViva.services.IproductoService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductoController {

    private final IproductoService productoService;
    private final IcategoriaService categoriaService;

    public ProductoController(IproductoService productoService, IcategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    @GetMapping("/productos")
    public List<Producto> obtenerTodos() {
        return  productoService.obtenerTodos();
    }

    @GetMapping("/productos/{id}")
    public Producto obtenerPorId(@PathVariable Long id) {
        return  productoService.obtenerPorId(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );
    }

    @PostMapping(value = "/productos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> crearProducto(
            @RequestParam String nombre,
            @RequestParam String descripcion,
            @RequestParam String fechaVencimiento,
            @RequestParam BigDecimal precioUnitario,
            @RequestParam String unidadDePeso,
            @RequestParam Integer stock,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam Long idCategoria
    ) throws IOException {

        if (!imagen.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("El archivo no es una imagen");
        }

        // 10 MB
        if (imagen.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("Imagen demasiado grande");
        }

        // Guardamos la imagen en el server
        String nombreArchivo = UUID.randomUUID() + "_" + imagen.getOriginalFilename();
        Path ruta = Paths.get("uploads", "images", nombreArchivo);
        Files.createDirectories(ruta.getParent());
        Files.copy(imagen.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setFechaVencimiento(LocalDate.parse(fechaVencimiento));
        producto.setPrecioUnitario(precioUnitario);
        producto.setUnidadDePeso(unidadDePeso);
        producto.setStock(stock);
        producto.setImagen("http://localhost:8080/images/" + nombreArchivo); // La ruta apunta al server

        Categoria categoria = categoriaService.obtenerPorId(idCategoria).orElseThrow(
                () -> new RuntimeException("Categoria no encontrada")
        );
        producto.setCategoria(categoria);

        productoService.crearProducto(producto);

        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/productos/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.ok("Producto eliminado con éxito");
    }

    @PostMapping("/productos/{productoId}/categorias/{categoriaId}")
    public Producto agregarCategoria(@PathVariable Long productoId, @PathVariable Long categoriaId) {
        return productoService.agregarCategoria(productoId, categoriaId);
    }

}
