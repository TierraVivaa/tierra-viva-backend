package com.tierraViva.tierraViva.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carritos")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "usuarioReference")
    private Usuario usuario_id;

    @OneToOne
    @JoinColumn(name = "pago_id", unique = true)
    @JsonBackReference(value = "pagoReference")
    private Pago pago_id;

    @OneToMany(mappedBy = "carrito")
    @JsonManagedReference
    private List<DetallePedido> detallesPedido;

    @Column(name = "precioTotal", precision = 10, scale = 2)
    private BigDecimal precioTotal;

    private LocalDateTime fechaPedido;

    // Constructor vacío
    public Carrito() {
    }

    // Constructor con parámetros
    public Carrito(Long idCarrito, Usuario usuario_id, Pago pago_id, List<DetallePedido> detallesPedido, BigDecimal precioTotal, LocalDateTime fechaPedido) {
        this.idCarrito = idCarrito;
        this.usuario_id = usuario_id;
        this.pago_id = pago_id;
        this.detallesPedido = detallesPedido;
        this.precioTotal = precioTotal;
        this.fechaPedido = fechaPedido;
    }

    // Getters y Setters
    public Long getIdCarrito() {
        return idCarrito;
    }

    public Usuario getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Usuario usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Pago getPago_id() {
        return pago_id;
    }

    public void setPago_id(Pago pago_id) {
        this.pago_id = pago_id;
    }

    public List<DetallePedido> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<DetallePedido> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public void setIdCarrito(Long idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Usuario getUsuario() {
        return usuario_id;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario_id = usuario;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public LocalDateTime getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDateTime fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
}