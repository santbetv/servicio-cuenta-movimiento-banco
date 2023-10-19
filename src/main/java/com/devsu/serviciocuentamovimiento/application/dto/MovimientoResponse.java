/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.serviciocuentamovimiento.application.dto;


import com.devsu.serviciocuentamovimiento.domain.Movimiento;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Santiago Betancur
 */
@Getter
@Setter
public class MovimientoResponse {

    private String fecha;
    private String nombre;
    private String numeroCuenta;
    private String tipoCuenta;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal tipoMovimiento;
    private BigDecimal saldo;

    public MovimientoResponse() {
    }

    public MovimientoResponse(Movimiento movimiento) {
        this.fecha = movimiento.getFecha().toString();
        this.nombre = movimiento.getObjClienteMovimiento().getNombre();
        this.numeroCuenta = movimiento.getObjCuentaMovimiento().getNumeroNuenta();
        this.tipoCuenta = movimiento.getObjCuentaMovimiento().getTipoCuenta();
        this.saldoInicial = movimiento.getObjCuentaMovimiento().getSaldoInicial();
        this.estado = movimiento.getObjCuentaMovimiento().getEstado();
        this.tipoMovimiento = movimiento.getValor();
        this.saldo = movimiento.getSaldo();
    }
    
    public MovimientoResponse build(Movimiento movimiento) {
        return new MovimientoResponse(movimiento);
    }

}
