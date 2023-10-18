package com.devsu.serviciocuentamovimiento.application.serviceimpl;

import com.devsu.serviciocuentamovimiento.domain.Cliente;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.CuentaEntity;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.repository.CuentaRepository;
import com.devsu.serviciocuentamovimiento.application.HandleMessage;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoRequest;
import com.devsu.serviciocuentamovimiento.application.dto.CuentaDtoResponse;
import com.devsu.serviciocuentamovimiento.domain.Cuenta;
import com.devsu.serviciocuentamovimiento.domain.port.out.db.ICuentaService;
import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.notification.Publisher;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Santiago Betancur
 */
@Service
public class CuentaServiceImpl implements ICuentaService {

    private static final Logger LOG = LoggerFactory.getLogger(CuentaServiceImpl.class);

    private final String INFO_URL = "api/cuenta";

    private CuentaRepository cuentaRepository;
    private HandleMessage handleMessage;
    private Publisher publisher;

    @Autowired
    public CuentaServiceImpl(CuentaRepository cuentaRepository, HandleMessage handleMessage, Publisher publisher) {
        this.cuentaRepository = cuentaRepository;
        this.handleMessage = handleMessage;
        this.publisher = publisher;
    }

    @Override
    @Transactional(readOnly = true) //
    public CuentaDtoResponse findById(Long id) {
        Optional<CuentaEntity> cuenta = cuentaRepository.findById(id);
        if (!cuenta.isEmpty()) {
            CuentaEntity c =cuentaRepository.findById(id).get();
            return null;
        } else {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @Override
    @Transactional(readOnly = true) //
    public List<CuentaEntity> findAll() {
        return cuentaRepository.findAll();
    }

    @Override
    @Transactional //
    public CuentaDtoResponse save(CuentaDtoRequest cuenta) {
        Cliente cliente = handleMessage.obtenerClientePorId(cuenta.getIdCliente());
        
        Cuenta c = new Cuenta();
        if (cliente != null) {
          c = Cuenta.builder().numeroNuenta(cuenta.getNumeroNuenta())
                    .tipoCuenta(cuenta.getTipoCuenta())
                    .saldoInicial(cuenta.getSaldoInicial())
                    .estado(cuenta.getEstado())
                    .cliente(cliente).build();
        } else {
            System.out.println("No se pudo asociar un cliente a la cuenta. El cliente con ID " + cuenta.getIdCliente() + " no fue encontrado.");
        }

        CuentaEntity ce= cuentaRepository.save(c.crearCuenta());
        
        c.setIdCuenta(ce.getIdCuenta());
        publisher.send(c);
        return new CuentaDtoResponse(c);

    }

}
