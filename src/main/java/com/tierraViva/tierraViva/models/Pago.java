package com.tierraViva.tierraViva.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @OneToOne(mappedBy = "pago_id", cascade = CascadeType.ALL)
    @JsonManagedReference // Evita bucles
    private Carrito carrito_id;

    @Column(length = 30)
    private String metodoPago;

    private LocalDate fechaPago;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    public Pago() {
    }

    public Pago(Long idPago, Carrito carrito_id, String metodoPago, LocalDate fechaPago, BigDecimal monto) {
        this.idPago = idPago;
        this.carrito_id = carrito_id;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public Carrito getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(Carrito carrito_id) {
        this.carrito_id = carrito_id;
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
