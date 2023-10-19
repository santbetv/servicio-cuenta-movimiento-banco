package com.devsu.serviciocuentamovimiento.domain.port.out.db;

import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleException;
import com.devsu.serviciocuentamovimiento.infrastructure.common.exception.BussinesRuleValidationException;
import java.util.List;
import org.springframework.validation.BindingResult;

/**
 *
 * @author Santiago Betancur
 */
public interface ICuentaService {

    public CuentaDtoResponse findById(Long id);

    public List<CuentaEntity> findAll();

    public CuentaDtoResponse save(CuentaDtoRequest cuenta, BindingResult result) throws BussinesRuleValidationException, BussinesRuleException;

    public void put(CuentaDtoRequest cuentaDTO, BindingResult result, Long id) throws BussinesRuleException, BussinesRuleValidationException;
    
    public void delete(Long id) throws BussinesRuleException;
}
