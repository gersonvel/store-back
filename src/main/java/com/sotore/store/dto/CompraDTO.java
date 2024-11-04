package com.sotore.store.dto;

import com.sotore.store.modelo.DetalleCompra;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompraDTO {

    private Integer idProveedor;
    private Float montoCompra;
    private List<DetalleCompraDTO> detalles;
}
