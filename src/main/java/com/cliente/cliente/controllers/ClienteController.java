package com.cliente.cliente.controllers;
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

import com.cliente.cliente.models.Cliente;
import com.cliente.cliente.repositories.ICliente;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ICliente iCliente;

    @PostMapping
    public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
        if (cliente.getId() != null) {
            return ResponseEntity.badRequest().build();
        }

        Cliente cliente1 = iCliente.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente1);
    }


    @GetMapping("/rfc")
    public ResponseEntity<List<Cliente>> obtenerClientePorrfc(@RequestParam(value = "rfc", required = false) String rfc) {
        List<Cliente> clientes;
        if (rfc == null) {
            clientes = iCliente.findAll();
        } else {
            clientes = iCliente.findByrfcContainingIgnoreCase(rfc);
        }

        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente clienteActualizar) {
        Optional<Cliente> clienteExiste = iCliente.findById(id);

        if (clienteExiste.isPresent()){
            Cliente cliente = clienteExiste.get();
            cliente.setCorreo(clienteActualizar.getCorreo());
            cliente.setContraseña(clienteActualizar.getContraseña());
            cliente.setNombre(clienteActualizar.getNombre());
            cliente.setRfc(clienteActualizar.getRfc());
            iCliente.save(cliente);
            return ResponseEntity.ok(cliente);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable("id") Long id) {
        Optional<Cliente> clienteExiste = iCliente.findById(id);

        if (clienteExiste.isPresent()) {
            iCliente.delete(clienteExiste.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
