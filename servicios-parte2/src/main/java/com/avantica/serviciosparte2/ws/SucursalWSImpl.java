/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.ws;

import com.avantica.serviciosparte2.dao.SucursalDao;
import com.avantica.serviciosparte2.dao.impl.SucursalDaoJpa;
import com.avantica.serviciosparte2.entity.Banco;
import com.avantica.serviciosparte2.entity.Sucursal;
import java.util.List;
import javax.jws.WebService;

/**
 *
 * @author branlap
 */
@WebService(endpointInterface = "com.avantica.serviciosparte2.ws.SucursalWS", serviceName = "SucursalWS")
public class SucursalWSImpl implements SucursalWS {

    private final SucursalDao<Sucursal> sucursalDao;

    public SucursalWSImpl() {
        this.sucursalDao = new SucursalDaoJpa(Sucursal.class);
    }
    
    @Override
    public List<Sucursal> buscarSucursales(String idBanco) {
        return sucursalDao.buscar(new Banco(Long.valueOf(idBanco)));
    }
    
}
