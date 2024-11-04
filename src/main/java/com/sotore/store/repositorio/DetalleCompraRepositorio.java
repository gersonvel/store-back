package com.sotore.store.repositorio;

import com.sotore.store.modelo.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleCompraRepositorio  extends JpaRepository<DetalleCompra,Integer> {
}
