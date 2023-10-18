/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.serviciocuentamovimiento.domain.port.in.web;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author rizzoli
 */
public interface WebPort {
    
    @GetMapping("/cuentas/{id}")
    CuentaDtoResponse get(@PathVariable Long id);
    
    @GetMapping("/cuentas")
    List<CuentaEntity> list();
    
    @PostMapping("/cuentas")
    ResponseEntity<?> post(@RequestBody CuentaDtoRequest input);
}
