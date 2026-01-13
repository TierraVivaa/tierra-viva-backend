package com.tierraViva.tierraViva.models;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(length = 50)
    private String nombre;

    @Column(length = 50)
    private String descripcion;

    @Column(name = "fechaVencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "precioUnitario", precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @Column(name = "unidadDePeso", length = 20)
    private String unidadDePeso;

    private Integer stock;

    @Column(length = 50)
    private String imagen;

    @Column(name = "id_categoria")
    private Long idCategoria;

    public Producto() {

    }

    public Producto(Long idProducto, String nombre, String descripcion, LocalDate fechaVencimiento, BigDecimal precioUnitario, String unidadDePeso, Integer stock, String imagen, Long idCategoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.precioUnitario = precioUnitario;
        this.unidadDePeso = unidadDePeso;
        this.stock = stock;
        this.imagen = imagen;
        this.idCategoria = idCategoria;
    }


    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getUnidadDePeso() {
        return unidadDePeso;
    }

    public void setUnidadDePeso(String unidadDePeso) {
        this.unidadDePeso = unidadDePeso;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }
}
