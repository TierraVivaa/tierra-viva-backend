package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Usuario;
import com.tierraViva.tierraViva.repositories.UsuariosRepository;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORTANTE
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IusuarioService {

    private final UsuariosRepository usuariosRepository;
    private final PasswordEncoder passwordEncoder; // Paso 1: Declarar el encriptador

    // Paso 2: Agregarlo al constructor
    public UsuarioService(UsuariosRepository usuariosRepository, PasswordEncoder passwordEncoder) {
        this.usuariosRepository = usuariosRepository;
        this.passwordEncoder = passwordEncoder;
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
        // Paso 3: Encriptar la contraseña antes de guardar
        String passwordEncriptada = passwordEncoder.encode(usuario.getContrasena());
        usuario.setContrasena(passwordEncriptada);

        return usuariosRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long id) {
        usuariosRepository.deleteById(id);
    }
}
