/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.ws;

import com.avantica.serviciosparte2.entity.Sucursal;
import java.util.List;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author branlap
 */
@WebService
public interface SucursalWS {
    
    List<Sucursal> buscarSucursales(@WebParam(name = "idBanco") String idBanco);
    
}
