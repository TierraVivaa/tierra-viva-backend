package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.dto.UserDto;
import com.tierraViva.tierraViva.repositories.UsuariosRepository;
import com.tierraViva.tierraViva.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // ¡Importante para que el frontend pueda conectar!
public class AuthController {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @PostMapping("/loginConDTO")
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        // 1. Buscar al usuario en la DB por el nombre de usuario
        Optional<Usuario> userOpt = usuarioRepository.findByUsuario(userDto.getUsuario());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();

            // 2. Comparar la contraseña (importante: usa .equals() para strings)
            if (user.getContrasena().equals(userDto.getContrasena())) {
                // Aquí deberías generar un JWT real, pero para probar usaremos un texto:
                return ResponseEntity.ok("TOKEN_DE_PRUEBA_EXITOSO");
            }
        }

        // 3. Si no existe o la contraseña no coincide
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }
}