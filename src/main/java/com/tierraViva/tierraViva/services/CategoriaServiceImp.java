package com.tierraViva.tierraViva.services;

import com.tierraViva.tierraViva.models.Categoria;
import com.tierraViva.tierraViva.repositories.CategoriaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImp implements IcategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImp(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> obtenerTodos() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void eliminarCategoria(Long id) {
        // Verificamos si existe antes de intentar borrar
        if (!categoriaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No se puede eliminar: La categoría con ID " + id + " no existe."
            );
        }
        categoriaRepository.deleteById(id);
    }
}