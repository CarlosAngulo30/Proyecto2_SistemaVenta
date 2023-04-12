package com.producto.producto.models;


import jakarta.persistence.*;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correo")
    private String correo;

    @Column(name = "RFC")
    private String rfc;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "contraseña")
    private String contraseña;

    public Cliente(String nombre, String correo, String rfc, String telefono, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.rfc = rfc;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public Cliente(Long id, String nombre, String correo, String rfc, String telefono, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.rfc = rfc;
        this.telefono = telefono;
        this.contraseña = contraseña;
    }

    public Cliente() {
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }


}
