package com.tierraViva.tierraViva.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pago;

    @OneToOne
    @JoinColumn(name = "carrito_id", unique = true)
    @JsonBackReference // Evita bucles
    private Carrito carrito;

    @Column(length = 30)
    private String metodoPago;

    private LocalDate fechaPago;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    public Pago() {
    }

    public Pago(Long id_pago, Carrito carrito, String metodoPago, LocalDate fechaPago, BigDecimal monto) {
        this.id_pago = id_pago;
        this.carrito = carrito;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Long getId_pago() {
        return id_pago;
    }

    public void setId_pago(Long id_pago) {
        this.id_pago = id_pago;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
