/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.rs;

import com.avantica.serviciosparte2.bean.ResponseBase;
import com.avantica.serviciosparte2.bean.SucursalRequestBean;
import com.avantica.serviciosparte2.dao.BancoDao;
import com.avantica.serviciosparte2.dao.SucursalDao;
import com.avantica.serviciosparte2.dao.impl.BancoDaoJpa;
import com.avantica.serviciosparte2.dao.impl.SucursalDaoJpa;
import com.avantica.serviciosparte2.entity.Banco;
import com.avantica.serviciosparte2.entity.Sucursal;
import static com.avantica.serviciosparte2.util.Constantes.FORMATO_FECHA_DD_MM_MYYYY;
import static com.avantica.serviciosparte2.util.Util.parsearFecha;
import static com.avantica.serviciosparte2.util.Util.printPrettyJSONString;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author branlap
 */
@Path("sucursal")
public class MantenimientoSucursal {
    
    private final SucursalDao<Sucursal> sucursalDao;
    private final BancoDao bancoDao;

    public MantenimientoSucursal() {
        this.sucursalDao = new SucursalDaoJpa(Sucursal.class);
        this.bancoDao = new BancoDaoJpa(Banco.class);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase crear(SucursalRequestBean request) {
        Banco banco = bancoDao.buscar(Long.valueOf(request.getIdBanco()));
        if (banco == null) {
            return new ResponseBase(false);
        }
        
        sucursalDao.crear(new Sucursal(request.getNombre(), request.getDireccion(), 
                parsearFecha(request.getFechaRegistro(), FORMATO_FECHA_DD_MM_MYYYY), banco));
        return new ResponseBase(true);
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase actualizar(@PathParam("id") String id, SucursalRequestBean request) {
        Banco banco = bancoDao.buscar(Long.valueOf(request.getIdBanco()));
        if (banco == null) {
            return new ResponseBase(false);
        }
        
        Sucursal sucursal = sucursalDao.buscar(Long.valueOf(id));
        if (sucursal == null) {
            return new ResponseBase(false);
        }
        
        sucursalDao.actualizar(new Sucursal(Long.valueOf(id), request.getNombre(), request.getDireccion(), 
                parsearFecha(request.getFechaRegistro(), FORMATO_FECHA_DD_MM_MYYYY), banco));
        return new ResponseBase(true);
    }
    
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public ResponseBase eliminar(@PathParam("id") String id) {
        Sucursal sucursal = sucursalDao.buscar(Long.valueOf(id));
        if (sucursal == null) {
            return new ResponseBase(false);
        }
        
        sucursalDao.eliminar(Long.valueOf(id));
        return new ResponseBase(true);
    }
    
    @GET
    @Path("listado")
    @Produces("application/json")
    public Response listar() {
        List<Sucursal> listaSucursales = sucursalDao.listar();
        return Response.ok().entity(printPrettyJSONString(listaSucursales)).build();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response buscar(@PathParam("id") String id) {
        Sucursal sucursal = sucursalDao.buscar(Long.valueOf(id));
        return Response.ok().entity(printPrettyJSONString(sucursal)).build();
    }
    
}
