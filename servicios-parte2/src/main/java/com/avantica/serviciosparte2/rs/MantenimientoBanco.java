/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.rs;

import com.avantica.serviciosparte2.bean.BancoRequestBean;
import com.avantica.serviciosparte2.bean.ResponseBase;
import com.avantica.serviciosparte2.dao.BancoDao;
import com.avantica.serviciosparte2.dao.impl.BancoDaoJpa;
import com.avantica.serviciosparte2.entity.Banco;
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
@Path("banco")
public class MantenimientoBanco {
    
    private final BancoDao bancoDao;

    public MantenimientoBanco() {
        this.bancoDao = new BancoDaoJpa(Banco.class);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase crear(BancoRequestBean request) {
        bancoDao.crear(new Banco(request.getNombre(), request.getDireccion(), 
                parsearFecha(request.getFechaRegistro(), FORMATO_FECHA_DD_MM_MYYYY)));
        return new ResponseBase(true);
    }
    
    @PUT
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase actualizar(@PathParam("id") String id, BancoRequestBean request) {
        Banco banco = bancoDao.buscar(Long.valueOf(id));
        if (banco == null) {
            return new ResponseBase(false);
        }
        
        bancoDao.actualizar(new Banco(Long.valueOf(id), request.getNombre(), request.getDireccion(), 
                parsearFecha(request.getFechaRegistro(), FORMATO_FECHA_DD_MM_MYYYY)));
        return new ResponseBase(true);
    }
    
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public ResponseBase eliminar(@PathParam("id") String id) {
        Banco banco = bancoDao.buscar(Long.valueOf(id));
        if (banco == null) {
            return new ResponseBase(false);
        }
        
        bancoDao.eliminar(Long.valueOf(id));
        return new ResponseBase(true);
    }
    
    @GET
    @Path("listado")
    @Produces("application/json")
    public Response listar() {
        List<Banco> listaBancos = bancoDao.listar();
        return Response.ok().entity(printPrettyJSONString(listaBancos)).build();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response buscar(@PathParam("id") String id) {
        Banco banco = bancoDao.buscar(Long.valueOf(id));
        return Response.ok().entity(printPrettyJSONString(banco)).build();
    }
    
}
