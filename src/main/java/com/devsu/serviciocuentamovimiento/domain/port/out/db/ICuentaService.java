package com.devsu.serviciocuentamovimiento.domain.port.out.db;

import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import java.util.List;

/**
 *
 * @author Santiago Betancur
 */
public interface ICuentaService {

    public CuentaDtoResponse findById(Long id);

    public List<CuentaEntity> findAll();

    public CuentaDtoResponse save(CuentaDtoRequest cuenta);


}
