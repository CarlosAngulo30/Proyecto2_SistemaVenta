package com.inventario.inventario.models;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "detalle")
public class DetalleVenta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne()
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne()
    @JoinColumn(name = "id_producto")
    private Producto producto;

    public DetalleVenta(Integer id, BigDecimal cantidad, BigDecimal total, Venta venta, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.venta = venta;
        this.producto = producto;
    }

    public DetalleVenta() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
