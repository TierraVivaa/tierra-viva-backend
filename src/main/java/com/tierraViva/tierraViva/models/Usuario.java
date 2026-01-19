package com.tierraViva.tierraViva.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, nullable = false)
    private String usuario;

    @Email
    @Column(length = 50, nullable = false)
    private String email;

    private Long numeroCelular;

    @NotBlank
    @Column(name = "contraseña")
    private String contrasena;

    @OneToMany(mappedBy = "usuarioCarrito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Carrito> carrito;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String usuario, String email, Long numeroCelular, String contrasena, List<Carrito> carrito) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.numeroCelular = numeroCelular;
        this.contrasena = contrasena;
        this.carrito = carrito;
    }

    public List<Carrito> getCarrito() {
        return carrito;
    }

    public void setCarrito(List<Carrito> carrito) {
        this.carrito = carrito;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(Long numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
