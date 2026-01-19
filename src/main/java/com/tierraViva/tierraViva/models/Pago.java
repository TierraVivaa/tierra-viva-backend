package com.tierraViva.tierraViva.models;

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


    @OneToOne(mappedBy = "pagoCarrito", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Carrito carritoPago;

    @Column(length = 30)
    private String metodoPago;

    private LocalDate fechaPago;

    @Column(name = "monto", precision = 10, scale = 2)
    private BigDecimal monto;

    public Pago() {
    }

    public Pago(Long idPago, Carrito carritoPago, String metodoPago, LocalDate fechaPago, BigDecimal monto) {
        this.idPago = idPago;
        this.carritoPago = carritoPago;
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

    public Carrito getCarritoPago() {
        return carritoPago;
    }

    public void setCarritoPago(Carrito carritoPago) {
        this.carritoPago = carritoPago;
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
