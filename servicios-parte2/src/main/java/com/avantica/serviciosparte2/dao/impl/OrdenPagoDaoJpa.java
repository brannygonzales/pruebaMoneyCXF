/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.dao.impl;

import com.avantica.serviciosparte2.crud.CrudJpa;
import com.avantica.serviciosparte2.dao.OrdenPagoDao;
import com.avantica.serviciosparte2.entity.OrdenPago;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author branlap
 */
public class OrdenPagoDaoJpa extends CrudJpa<OrdenPago> implements OrdenPagoDao<OrdenPago> {
    
    private final Class<OrdenPago> objeto;
    
    public OrdenPagoDaoJpa(Class<OrdenPago> objeto) {
        super(objeto);
        this.objeto = objeto;
    }

    @Override
    public List<OrdenPago> listar(String idSucursal, String moneda) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<OrdenPago> cq = cb.createQuery(objeto);
        Root<OrdenPago> selectOrdenPago = cq.from(objeto);
        cq.select(selectOrdenPago).where(
                cb.equal(selectOrdenPago.get("idSucursal"), idSucursal), 
                cb.equal(selectOrdenPago.get("moneda"), moneda));
        TypedQuery<OrdenPago> q = em.createQuery(cq);
        List<OrdenPago> lista = q.getResultList();
        return lista;
    }
    
}
