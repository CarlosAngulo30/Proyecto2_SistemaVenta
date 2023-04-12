package controllers;
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

import com.inventario.inventario.models.Inventario;
import com.inventario.inventario.repositories.IInventario;


@Controller
@RequestMapping("/inventario")
public class InventarioController {
    @Autowired
    private IInventario iInventario;

    @PostMapping
    public ResponseEntity<Inventario> agregarInventario(@RequestBody Inventario inventario) {
        if (inventario.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Inventario inventario1 = iInventario.save(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario1);
    }


    @GetMapping("/{idProducto}")
    public ResponseEntity<List<Inventario>> obtenerInventarioPorProducto(@RequestParam(value = "id_producto", required = false) Long idProducto) {
        List<Inventario> inventarios;
        if (idProducto == null) {
            inventarios = iInventario.findAll();
        } else {
            inventarios = iInventario.findByProducto(idProducto);
        }

        if (inventarios.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(inventarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable("id") Long id, @RequestBody Inventario inventarioActualizar) {
        Optional<Inventario> inventarioExiste = iInventario.findById(id);

        if (inventarioExiste.isPresent()) {
            Inventario inventario = inventarioExiste.get();
                inventario.setProducto(inventarioActualizar.getProducto());
                inventario.setCantidad(inventarioActualizar.getCantidad());
            iInventario.save(inventario);
            return ResponseEntity.ok(inventario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(@PathVariable("id") Long id) {
        Optional<Inventario> inventarioExiste = iInventario.findById(id);

        if (inventarioExiste.isPresent()) {
            iInventario.delete(inventarioExiste.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
