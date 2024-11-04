package com.sotore.store.dto;

import com.sotore.store.modelo.Compra;
import com.sotore.store.modelo.DetalleCompra;
import com.sotore.store.modelo.Producto;
import com.sotore.store.modelo.Proveedor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetalleCompraDTO {

    private Integer cantidad;
    private Integer precio_unitario;
    private Integer total;
    private Integer id_compra;
    private Integer id_producto;
    private Integer id_proveedor;
}
