package com.tierraViva.tierraViva.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    private Long idUsuario;

    private BigDecimal precioTotal;

    private LocalDateTime fecha;

    // Constructor vacío
    public Carrito() {
    }

    // Constructor con parámetros
    public Carrito(Long idCarrito, Long idUsuario, BigDecimal precioTotal, LocalDateTime fecha) {
        this.idCarrito = idCarrito;
        this.idUsuario = idUsuario;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}