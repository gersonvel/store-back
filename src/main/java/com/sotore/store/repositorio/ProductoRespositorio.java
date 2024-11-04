package com.sotore.store.repositorio;

import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRespositorio extends JpaRepository<Producto, Integer> {

    // Método que Spring Data JPA implementará automáticamente
    List<Producto> findByProveedor(Proveedor proveedor);
}
