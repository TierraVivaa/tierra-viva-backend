package com.tierraViva.tierraViva.models;

import jakarta.persistence.*;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetallePedido;

    @Column(nullable = false)
    private int idProducto;

    @Column(nullable = false)
    private int cantidad;

    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;


    public DetallePedido() {
    }

    public int getIdDetallePedido() {
        return idDetallePedido;
    }

    public void setIdDetallePedido(int idDetallePedido) {
        this.idDetallePedido = idDetallePedido;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
