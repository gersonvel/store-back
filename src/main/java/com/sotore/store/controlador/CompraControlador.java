package com.sotore.store.controlador;



import com.sotore.store.dto.CompraDTO;
import com.sotore.store.dto.DetalleCompraDTO;
import com.sotore.store.excepcion.RecursoNoEncontradoExcepcion;
import com.sotore.store.modelo.Compra;
import com.sotore.store.modelo.DetalleCompra;
import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import com.sotore.store.repositorio.ProveedorRepositorio;
import com.sotore.store.servicio.CompraServicio;
import com.sotore.store.servicio.DetalleCompraServicio;
import com.sotore.store.servicio.ProductoServicio;
import com.sotore.store.servicio.ProveedorServicio;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/store") //puede que se tenga que configurar diferente si se utiliza el jwt
@CrossOrigin(value = "http://localhost:3000")
public class CompraControlador {
    private static final Logger logger = LoggerFactory.getLogger(CompraControlador.class);
    @Autowired
    private CompraServicio compraServicio;

    @Autowired
    //si no funciona lo dejamos con IProveedorServicio
    private ProveedorServicio proveedorServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private DetalleCompraServicio detalleCompraServicio;

    @GetMapping("/compras")
    public List<Compra> obtenerCompras(){
        var compras = compraServicio.listarCompras();
        compras.forEach(compra -> {
            logger.info(compra.toString());

        });
        return compras;
    }

   /* @PostMapping("/compra")
    public Compra agregarCompra(@RequestBody Compra compra){

        logger.info("Copraaaaaa", compra);
        Date fechaRegistroCompra = new Date();
        compra.setFechaCompra(fechaRegistroCompra);
        logger.info("Compra a agregar: " + compra);
        return compraServicio.guardarCompra(compra);
    }*/

    @PostMapping("/compra")
    @Transactional
    public Compra agregarCompra(@RequestBody CompraDTO compraDTO){

        Proveedor proveedor = proveedorServicio.buscarProveedorPorId(compraDTO.getIdProveedor());
        if (proveedor == null) throw new RecursoNoEncontradoExcepcion("El id recibido no existe: "+compraDTO.getIdProveedor());

       logger.info("Datos de la compra recibidos: {}",  compraDTO.toString());

        Date fechaRegistroCompra = new Date();

        Compra compra = new Compra();
        compra.setFechaCompra(fechaRegistroCompra);
        compra.setProveedor(proveedor); // Asigna el proveedor encontrado
        compra.setMontoCompra(compraDTO.getMontoCompra());

        Compra compraGuardada = compraServicio.guardarCompra(compra);

       for (DetalleCompraDTO detalleCompraDTO : compraDTO.getDetalles()) {
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setCantidad(detalleCompraDTO.getCantidad());
            detalleCompra.setPrecioUnitario(detalleCompraDTO.getPrecio_unitario());
            detalleCompra.setTotal(detalleCompraDTO.getTotal());
            detalleCompra.setCompra(compraGuardada);
            detalleCompra.setProducto(productoServicio.buscarProductoPorId(detalleCompraDTO.getId_producto()));
            detalleCompra.setProveedor(proveedor);

           detalleCompraServicio.guardarDetalleCompra(detalleCompra);

            logger.info("AQUI DEBE DE MOSTRAR EL DETALLE" + detalleCompra.toString());
        }



        logger.info("Datos de la compra : {}",  compra.toString());

        //return compraServicio.guardarCompra(compra);
        return compraGuardada;
    }
}
