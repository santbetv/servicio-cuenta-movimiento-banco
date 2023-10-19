/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.repository;

import com.devsu.serviciocuentamovimiento.infrastructure.adapter.out.db.model.MovimientoEntity;
import java.math.BigDecimal;
import java.util.List;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Santiago Betancur
 */
@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    @Query(value = "SELECT saldo FROM MOVIMIENTOS where ID_CLIENTE =?1 and ID_CUENTA =?2 order by ID_MOVIMIENTO desc limit 1", nativeQuery = true)
    public BigDecimal ultimoSaldo(Long idCliente, Long idCuenta);
    
    @Query("SELECT m FROM MovimientoEntity m JOIN FETCH m.objCuentaMovimiento")
    @Fetch(FetchMode.SUBSELECT)
    public List<MovimientoEntity> datosCliente();

}
