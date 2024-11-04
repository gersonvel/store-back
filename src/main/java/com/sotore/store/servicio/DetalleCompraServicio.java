package com.sotore.store.servicio;

import com.sotore.store.modelo.DetalleCompra;
import com.sotore.store.repositorio.DetalleCompraRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleCompraServicio implements IDetalleCompraServicio{


    @Autowired
    private DetalleCompraRepositorio detalleCompraRepositorio;


    @Override
    public DetalleCompra guardarDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepositorio.save(detalleCompra);
    }
}
