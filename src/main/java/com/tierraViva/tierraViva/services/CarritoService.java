package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Carrito;
import com.tierraViva.tierraViva.models.Usuario;
import com.tierraViva.tierraViva.repositories.CarritoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarritoService implements IcarritoService {

    private final CarritoRepository carritoRepository;
    private final IusuarioService usuarioService;
    private final IcarritoService carritoService;

    public CarritoService(CarritoRepository carritoRepository, IusuarioService usuarioService, IcarritoService carritoService) {
        this.carritoRepository = carritoRepository;
        this.usuarioService = usuarioService;
        this.carritoService = carritoService;
    }

    @Override
    public Carrito crearCarrito(Carrito carrito) {
        return carritoRepository.save(carrito);
    }

    @Override
    public void eliminarCarrito(Long id) {
        carritoRepository.deleteById(id);
    }

    @Override
    public Optional<Carrito> obtenerPorId(Long id) {
        return carritoRepository.findById(id);
    }

    @Override
    public Carrito asignarUsuario(Long idUsurio, Long idCarrito) {
        Usuario usuario = usuarioService.obtenerPorId(idUsurio).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );

        Carrito carrito = carritoService.obtenerPorId(idCarrito).orElseThrow(
                () -> new RuntimeException("Carrito no encontrado")
        );

        carrito.setUsuarioCarrito(usuario);

        return carritoRepository.save(carrito);
    }
}
