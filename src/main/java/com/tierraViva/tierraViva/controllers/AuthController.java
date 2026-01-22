package com.tierraViva.tierraViva.controllers;

import com.tierraViva.tierraViva.JwtUtil;
import com.tierraViva.tierraViva.dto.UserDto;
import com.tierraViva.tierraViva.repositories.UsuariosRepository;
import com.tierraViva.tierraViva.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuariosRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/loginConDTO")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        // 1. Buscar al usuario en la DB por el nombre de usuario
        Optional<Usuario> userOpt = usuarioRepository.findByUsuario(userDto.getUsuario());

        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();

            if (passwordEncoder.matches(userDto.getContrasena(), user.getContrasena())) {
                String token = jwtUtil.generateToken(user.getUsuario());
                System.out.println("El usuario ingresó exitosamente");
                return ResponseEntity.ok(token);
            }
        }

        // 3. Si no existe o la contraseña no coincide
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }
}