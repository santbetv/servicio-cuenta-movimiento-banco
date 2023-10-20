/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.serviciocuentamovimiento.infrastructure.adapter.in.web;

import com.devsu.serviciocuentamovimiento.application.dto.MovimientoDTO;
import com.devsu.serviciocuentamovimiento.application.dto.MovimientoResponse;
import com.devsu.serviciocuentamovimiento.domain.port.in.web.WebPortMovimientos;
import com.devsu.serviciocuentamovimiento.domain.port.out.db.IMovimientoService;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.MovimientoEntity;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleException;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleMovimientoValidationException;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleValidationException;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Santiago Betancur
 */
@RestController
@RequestMapping("/api")
public class MovimientoRestController implements WebPortMovimientos {

    private static final Logger LOG = LoggerFactory.getLogger(MovimientoRestController.class);

    private IMovimientoService iMovimientoService;

    @Autowired
    public MovimientoRestController(IMovimientoService iMovimientoService) {
        this.iMovimientoService = iMovimientoService;
    }

    @Override
    @GetMapping("/movimientos")
    public List<MovimientoEntity> list() {
        return iMovimientoService.findAll();
    }

    @Override
    @GetMapping("/movimientos/{id}")
    public MovimientoEntity get(@PathVariable Long id) throws BussinesRuleException {
        return iMovimientoService.findById(id);
    }

    @Override
    @GetMapping("/movimientos/fecha/{fecha}/cliente/{id}")
    public List<MovimientoResponse> gets(@PathVariable String fecha, @PathVariable Long id) {
        return iMovimientoService.findByFechaAndByCustomer(fecha, id);
    }

    @Override
    @PostMapping("/movimientos")
    public ResponseEntity<?> post(@Valid @RequestBody MovimientoDTO input, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException, BussinesRuleMovimientoValidationException {
        MovimientoResponse save = iMovimientoService.save(input, result);
        return ResponseEntity.ok(save);
    }

    @Override
    @PutMapping("/movimientos/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody MovimientoDTO input, BindingResult result, @PathVariable Long id) throws BussinesRuleException, BussinesRuleValidationException {
        iMovimientoService.put(input, result, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/movimientos/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws BussinesRuleException {
        return new ResponseEntity(HttpStatus.OK);
    }

}
