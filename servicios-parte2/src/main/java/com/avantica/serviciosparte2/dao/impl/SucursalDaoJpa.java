/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.dao.impl;

import com.avantica.serviciosparte2.crud.CrudJpa;
import com.avantica.serviciosparte2.dao.SucursalDao;
import com.avantica.serviciosparte2.entity.Banco;
import com.avantica.serviciosparte2.entity.Sucursal;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author branlap
 */
public class SucursalDaoJpa extends CrudJpa<Sucursal> implements SucursalDao<Sucursal> {
    
    private final Class<Sucursal> objeto;
    
    public SucursalDaoJpa(Class<Sucursal> objeto) {
        super(objeto);
        this.objeto = objeto;
    }

    @Override
    public List<Sucursal> buscar(Banco banco) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Sucursal> cq = cb.createQuery(objeto);
        Root<Sucursal> selectSucursal = cq.from(objeto);
        cq.select(selectSucursal).where(
                cb.equal(selectSucursal.get("banco"), banco));
        TypedQuery<Sucursal> q = em.createQuery(cq);
        List<Sucursal> lista = q.getResultList();
        return lista;
    }
    
}
