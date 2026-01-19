package com.tierraViva.tierraViva.dto;

public class UserDto {
    private String usuario;
    private String contrasena;

    // Constructor vacío (necesario para Jackson/JSON)
    public UserDto() {
    }

    public UserDto(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    // Getters y Setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
}