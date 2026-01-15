package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Usuario;
import com.tierraViva.tierraViva.repositories.UsuariosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IusuarioService {

    private final UsuariosRepository usuariosRepository;

    public UsuarioService(UsuariosRepository usuariosRepository) {
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    @Override
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuariosRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuariosRepository.deleteById(id);
    }
}
