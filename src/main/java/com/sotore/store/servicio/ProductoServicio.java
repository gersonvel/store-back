package com.sotore.store.servicio;

import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import com.sotore.store.repositorio.ProductoRespositorio;
import com.sotore.store.repositorio.ProveedorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServicio implements IProductoServicio {

    @Autowired
    private ProductoRespositorio productoRespositorio;

    @Autowired
    private ProveedorRepositorio proveedorRepositorio;

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRespositorio.save(producto);
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRespositorio.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer idProductor) {
        Producto producto = productoRespositorio.findById(idProductor).orElse(null);
        return producto;
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRespositorio.delete(producto);
    }

    @Override
    public List<Producto> obtenerProductosPorIdProveedor(Integer idProveedor) {
        Proveedor proveedor = proveedorRepositorio.findById(idProveedor).orElse(null);

        return productoRespositorio.findByProveedor(proveedor);
    }
}
