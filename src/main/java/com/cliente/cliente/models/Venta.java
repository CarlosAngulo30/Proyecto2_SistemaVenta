package com.cliente.cliente.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private BigDecimal total;

    @Column(name = "folio")
    private String folio;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    public Venta(BigDecimal total, String folio, LocalDateTime fecha, Cliente cliente) {
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Venta(Long id, BigDecimal total, String folio, LocalDateTime fecha, Cliente cliente) {
        this.id = id;
        this.total = total;
        this.folio = folio;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public Venta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
