package com.venta.venta.models;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "Producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    
    private String marca;
    
    private BigDecimal precio;
    
    private String codigo;
    
    @ManyToOne
    @JoinColumn(name = "id_unidad_medida", referencedColumnName = "id")
    private UnidadMedida unidadMedida;

    public Producto(String nombre, String marca, BigDecimal precio, String codigo, UnidadMedida unidadMedida) {
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.codigo = codigo;
        this.unidadMedida = unidadMedida;
    }

    public Producto(Long id, String nombre, String marca, BigDecimal precio, String codigo, UnidadMedida unidadMedida) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.codigo = codigo;
        this.unidadMedida = unidadMedida;
    }

    public Producto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
    

}
