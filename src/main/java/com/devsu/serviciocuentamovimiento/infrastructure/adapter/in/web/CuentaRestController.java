package com.devsu.serviciocuentamovimiento.infrastructure.adapter.in.web;


import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import com.devsu.serviciocuentamovimiento.domain.port.out.db.ICuentaService;
import com.devsu.serviciocuentamovimiento.domain.port.in.web.WebPort;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Santiago Betancur
 */
@RestController
@RequestMapping("/api")
public class CuentaRestController implements WebPort{

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
    public ResponseEntity<?> post(CuentaDtoRequest input) {
        CuentaDtoResponse save = iCuentaService.save(input);
        return ResponseEntity.ok(save);
    }
}
