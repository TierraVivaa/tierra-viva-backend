package com.tierraViva.tierraViva.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    // MERCADO PAGO

    @Column(name = "estado_pago")
    private String estadoPago;
    // PENDIENTE, APROBADO, RECHAZADO

    @Column(name = "mp_preference_id", length = 50)
    private String mpPreferenceId;

    @Column(name = "mp_payment_id")
    private String mpPaymentId;

    private LocalDateTime fechaCreacion;

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

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getMpPreferenceId() {
        return mpPreferenceId;
    }

    public void setMpPreferenceId(String mpPreferenceId) {
        this.mpPreferenceId = mpPreferenceId;
    }

    public String getMpPaymentId() {
        return mpPaymentId;
    }

    public void setMpPaymentId(String mpPaymentId) {
        this.mpPaymentId = mpPaymentId;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
