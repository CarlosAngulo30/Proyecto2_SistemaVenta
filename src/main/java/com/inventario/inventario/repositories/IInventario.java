package com.inventario.inventario.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventario.inventario.models.Inventario;

@Repository
public interface IInventario extends JpaRepository<Inventario,Long>{
    List<Inventario> findByProducto(Long producto);
}
