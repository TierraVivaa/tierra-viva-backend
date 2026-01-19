package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.DetallePedido;
import com.tierraViva.tierraViva.models.Producto;
import com.tierraViva.tierraViva.repositories.CarritoRepository;
import com.tierraViva.tierraViva.repositories.DetallePedidoRepository;
import com.tierraViva.tierraViva.repositories.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service


public class DetallePedidoService implements IdetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final CarritoRepository carritoRepository;
    private final ProductoRepository productoRepository;

    public DetallePedidoService(
            DetallePedidoRepository detallePedidoRepository,
            CarritoRepository carritoRepository,
            ProductoRepository productoRepository
    ) {
        this.detallePedidoRepository = detallePedidoRepository;
        this.carritoRepository = carritoRepository;
        this.productoRepository = productoRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<DetallePedido> obtenerTodos() {
        List<DetallePedido> detalles = detallePedidoRepository.findAll();


        if (!detalles.isEmpty()) {
            detalles.get(0).getProducto();
            detalles.get(0).getCarrito();
        }

        return detalles;
    }


    @Transactional(readOnly = true)
    @Override
    public Optional<DetallePedido> obtenerPorId(Long id) {

        DetallePedido detalle = detallePedidoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Detalle de pedido no encontrado")
        );

        detalle.getProducto();
        detalle.getCarrito();

        return Optional.of(detalle);
    }

//transaccion
    @Transactional
    @Override
    public DetallePedido crearDetallePedido(Long idCarrito, Long idProducto, int cantidad) {

        if (cantidad <= 0) {
            throw new RuntimeException("La cantidad debe ser mayor a 0");
        }

        Carrito carrito = carritoRepository.findById(idCarrito).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );

        Producto producto = productoRepository.findById(idProducto).orElseThrow(
                () -> new RuntimeException("Producto no encontrado")
        );

        if (producto.getStock() < cantidad) {
            throw new RuntimeException("Stock insuficiente");
        }


        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);

        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(cantidad);
        detallePedido.setCarrito(carrito);
        detallePedido.setProducto(producto);

        return detallePedidoRepository.save(detallePedido);
    }
}