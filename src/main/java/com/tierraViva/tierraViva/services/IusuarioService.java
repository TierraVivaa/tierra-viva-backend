package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface IusuarioService {

    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(Long id);
    Usuario crearUsuario(Usuario usuario);
    void eliminarUsuario(Long id);

}
