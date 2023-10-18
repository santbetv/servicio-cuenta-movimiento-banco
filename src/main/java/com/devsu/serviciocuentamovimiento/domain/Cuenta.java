/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.serviciocuentamovimiento.domain;

import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author rizzoli
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cuenta {
    
    private Long idCuenta;
    private String numeroNuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private Cliente cliente;
    
    public CuentaEntity crearCuenta(){
        return new CuentaEntity(idCuenta, numeroNuenta, tipoCuenta, saldoInicial, estado, cliente.getIdCliente());
    }
    
}
