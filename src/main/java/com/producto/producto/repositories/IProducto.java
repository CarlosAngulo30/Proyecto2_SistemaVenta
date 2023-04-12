package com.producto.producto.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.producto.producto.models.Producto;

@Repository
public interface IProducto extends JpaRepository<Producto,Long>{
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
    List<Producto> findByCodigoContainingIgnoreCase(String codigo);
}
