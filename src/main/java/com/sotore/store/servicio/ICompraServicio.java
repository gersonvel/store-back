package com.sotore.store.servicio;

import com.sotore.store.modelo.Categoria;
import com.sotore.store.modelo.Compra;
import com.sotore.store.modelo.Producto;

import java.util.List;

public interface ICompraServicio {

    public List<Compra> listarCompras();

    public Compra guardarCompra(Compra compra);

}
