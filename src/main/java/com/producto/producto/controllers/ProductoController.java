package com.producto.producto.controllers;

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

import com.producto.producto.models.Producto;
import com.producto.producto.repositories.IProducto;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private IProducto iProducto;

    @PostMapping
    public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
        if (producto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Producto producto1 = iProducto.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(producto1);
    }


    @GetMapping("/marca")
    public ResponseEntity<List<Producto>> obtenerProductosPorMarca(@RequestParam(value = "marca", required = false) String marca) {
        List<Producto> productos;
        if (marca == null) {
            productos = iProducto.findAll();
        } else {
            productos = iProducto.findByMarcaContainingIgnoreCase(marca);
        }

        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/codigo")
    public ResponseEntity<List<Producto>> obtenerProductosPorCodigo(@RequestParam(value = "codigo", required = false) String codigo) {
        List<Producto> productos;
        if (codigo == null) {
            productos = iProducto.findAll();
        } else {
            productos = iProducto.findByCodigoContainingIgnoreCase(codigo);
        }

        if (productos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductosPorId(@PathVariable("id") Long id) {
        Optional<Producto> producto = iProducto.findById(id);

        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long id, @RequestBody Producto productoActualizar) {
        Optional<Producto> productoExiste = iProducto.findById(id);

        if (productoExiste.isPresent()) {
            Producto producto = productoExiste.get();
                producto.setUnidadMedida(productoActualizar.getUnidadMedida());
                producto.setNombre(productoActualizar.getNombre());
                producto.setCodigo(productoActualizar.getCodigo());
                producto.setPrecio(productoActualizar.getPrecio());
                producto.setMarca(productoActualizar.getMarca());
            iProducto.save(producto);
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable("id") Long id) {
        Optional<Producto> productoExiste = iProducto.findById(id);

        if (productoExiste.isPresent()) {
            iProducto.delete(productoExiste.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
