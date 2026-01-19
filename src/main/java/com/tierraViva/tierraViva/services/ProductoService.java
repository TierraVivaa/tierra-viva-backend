package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.repositories.CategoriaRepository;
import com.tierraViva.tierraViva.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IproductoService{
    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Producto> obtenerTodos() {
        List<Producto> producto = productoRepository.findAll();

        if(!producto.isEmpty()) {
            producto.get(0).getCategoria();
        }

        return producto;
    }

    @Override
    public Optional<Producto> obtenerPorId(Long id) {

        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );

        producto.getCategoria();

        return Optional.of(producto);
    }

    @Override
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );

        productoRepository.delete(producto);
    }

    @Override
    public Producto agregarCategoria(Long productoId, Long categoriaId) {
        Producto producto = productoRepository.findById(productoId).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );

        Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(
                () -> new RuntimeException("Categoria no encontrada")
        );

        producto.setCategoria(categoria);

        return productoRepository.save(producto);
    }
}
