package com.venta.venta.controllers;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.venta.venta.models.Venta;
import com.venta.venta.repositories.IVenta;

@Controller
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private IVenta iVenta;

    @PostMapping
    public ResponseEntity<Venta> agregarVenta(@RequestBody Venta venta) {
        if (venta.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Venta venta1 = iVenta.save(venta);
        return ResponseEntity.status(HttpStatus.CREATED).body(venta1);
    }


    @GetMapping("/ventasPorFolio")
    public ResponseEntity<List<Venta>> obtenerVentaPorFolio(@RequestParam(value = "folio", required = false) String folio) {
        List<Venta> ventas;
        if (folio == null) {
            ventas = iVenta.findAll();
        } else {
            ventas = iVenta.findByFolioContainingIgnoreCase(folio);
        }

        if (ventas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ventas);
    }

    /*@GetMapping("/query")
    public ResponseEntity<List<Venta>> obtenerVentaPorCliente(@RequestParam(value = "id_cliente", required = false) Long idCliente) {
        List<Venta> ventas;
        if (idCliente == null) {
            ventas = iVenta.findAll();
        } else {
            ventas = iVenta.findByIdCliente(idCliente);
        }

        if (ventas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ventas);
    }*/


    @GetMapping("/ventasPorFecha")
    public ResponseEntity<List<Venta>> obtenerVentaPorFecha(@RequestParam(value = "fecha", required = false) Calendar fecha) {
        List<Venta> ventas;
        if (fecha == null) {
            ventas = iVenta.findAll();
        } else {
            ventas = iVenta.findByFecha(fecha);
        }

        if (ventas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ventas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizarVenta(@PathVariable("id") Long id, @RequestBody Venta ventaActualizar) {
        Optional<Venta> ventaExiste = iVenta.findById(id);

        if (ventaExiste.isPresent()){
            Venta venta = ventaExiste.get();
            venta.setFolio(ventaActualizar.getFolio());
            venta.setFecha(ventaActualizar.getFecha());
            venta.setTotal(ventaActualizar.getTotal());
            venta.setCliente(ventaActualizar.getCliente());
            iVenta.save(venta);
            return ResponseEntity.ok(venta);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable("id") Long id) {
        Optional<Venta> ventaExiste = iVenta.findById(id);

        if (ventaExiste.isPresent()) {
            iVenta.delete(ventaExiste.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
