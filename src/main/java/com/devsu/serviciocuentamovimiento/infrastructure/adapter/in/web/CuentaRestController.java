package com.devsu.serviciocuentamovimiento.infrastructure.adapter.in.web;

import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import com.devsu.serviciocuentamovimiento.domain.port.out.db.ICuentaService;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleException;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleValidationException;
import jakarta.validation.Valid;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsu.serviciocuentamovimiento.domain.port.in.web.WebPortCuentas;
import org.springframework.web.bind.annotation.DeleteMapping;

/**
 *
 * @author Santiago Betancur
 */
@RestController
@RequestMapping("/api")
public class CuentaRestController implements WebPortCuentas {

    private static final Logger LOG = LoggerFactory.getLogger(CuentaRestController.class);

    private ICuentaService iCuentaService;

    @Autowired
    public CuentaRestController(ICuentaService iCuentaService) {
        this.iCuentaService = iCuentaService;
    }

    @Override
    @GetMapping("/cuentas/{id}")
    public CuentaDtoResponse get(Long id) {
        return iCuentaService.findById(id);
    }

    @Override
    @GetMapping("/cuentas")
    public List<CuentaEntity> list() {
        return iCuentaService.findAll();
    }

    @Override
    @PostMapping("/cuentas")
    public ResponseEntity<?> post(@Valid @RequestBody CuentaDtoRequest input, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException {
        CuentaDtoResponse save = iCuentaService.save(input, result);
        return ResponseEntity.ok(save);
    }

    @Override
    @PutMapping("/cuentas/{id}")
    public ResponseEntity<?> put(@Valid @RequestBody CuentaDtoRequest input, BindingResult result, @PathVariable Long id) throws BussinesRuleException, BussinesRuleValidationException {
        iCuentaService.put(input, result, id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/cuentas/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws BussinesRuleException {
        iCuentaService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
