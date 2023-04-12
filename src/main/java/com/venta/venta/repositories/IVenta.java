package com.venta.venta.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venta.venta.models.Venta;

@Repository
public interface IVenta extends JpaRepository<Venta,Long>{
    List<Venta> findByFolioContainingIgnoreCase(String folio);
    List<Venta> findByFecha(Calendar fecha);
    //List<Venta> findByidCliente(Long idCliente);
}

