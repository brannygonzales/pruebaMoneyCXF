/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.rs;

import com.avantica.serviciosparte2.bean.OrdenPagoCambioRequestBean;
import com.avantica.serviciosparte2.bean.OrdenPagoNuevoRequestBean;
import com.avantica.serviciosparte2.bean.ResponseBase;
import com.avantica.serviciosparte2.dao.OrdenPagoDao;
import com.avantica.serviciosparte2.dao.SucursalDao;
import com.avantica.serviciosparte2.dao.impl.OrdenPagoDaoJpa;
import com.avantica.serviciosparte2.dao.impl.SucursalDaoJpa;
import com.avantica.serviciosparte2.entity.OrdenPago;
import com.avantica.serviciosparte2.entity.Sucursal;
import static com.avantica.serviciosparte2.enums.EstadoOrdenPagoEnum.obtenerEstadoOrdenPagoEnumName;
import static com.avantica.serviciosparte2.util.Constantes.FORMATO_FECHA_DD_MM_MYYYY;
import static com.avantica.serviciosparte2.util.Util.parsearFecha;
import static com.avantica.serviciosparte2.util.Util.printPrettyJSONString;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import static com.avantica.serviciosparte2.enums.MonedaEnum.obtenerMonedaEnumName;

/**
 *
 * @author branlap
 */
@Path("orden-pago")
public class MantenimientoOrdenPago {
    
    private final SucursalDao<Sucursal> sucursalDao;
    private final OrdenPagoDao<OrdenPago> ordenPagoDao; 

    public MantenimientoOrdenPago() {
        this.sucursalDao = new SucursalDaoJpa(Sucursal.class);
        this.ordenPagoDao = new OrdenPagoDaoJpa(OrdenPago.class);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase crear(OrdenPagoNuevoRequestBean request) {
        Sucursal sucursal = sucursalDao.buscar(Long.valueOf(request.getIdSucursal()));
        if (sucursal == null) {
            return new ResponseBase(false);
        }
        
        ordenPagoDao.crear(new OrdenPago(request.getMonto(), obtenerMonedaEnumName(request.getMoneda()), 
                request.getIdSucursal()));
        return new ResponseBase(true);
    }
    
    @PATCH
    @Path("{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public ResponseBase actualizar(@PathParam("id") String id, OrdenPagoCambioRequestBean request) {
        Sucursal sucursal = sucursalDao.buscar(Long.valueOf(request.getIdSucursalPago()));
        if (sucursal == null) {
            return new ResponseBase(false);
        }
        
        OrdenPago ordenPago = ordenPagoDao.buscar(Long.valueOf(id));
        if (ordenPago == null) {
            return new ResponseBase(false);
        }
        
        ordenPago.setEstado(obtenerEstadoOrdenPagoEnumName(request.getEstado()));
        ordenPago.setFechaPago(parsearFecha(request.getFechaPago(), FORMATO_FECHA_DD_MM_MYYYY));
        ordenPago.setIdSucursalPago(request.getIdSucursalPago());
        ordenPagoDao.actualizar(ordenPago);
        return new ResponseBase(true);
    }
    
    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public ResponseBase eliminar(@PathParam("id") String id) {
        OrdenPago ordenPago = ordenPagoDao.buscar(Long.valueOf(id));
        if (ordenPago == null) {
            return new ResponseBase(false);
        }
        
        ordenPagoDao.eliminar(Long.valueOf(id));
        return new ResponseBase(true);
    }
    
    @GET
    @Path("listado")
    @Produces("application/json")
    public Response listar() {
        List<OrdenPago> listaOrdenPago = ordenPagoDao.listar();
        return Response.ok().entity(printPrettyJSONString(listaOrdenPago)).build();
    }
    
    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response buscar(@PathParam("id") String id) {
        OrdenPago ordenPago = ordenPagoDao.buscar(Long.valueOf(id));
        return Response.ok().entity(printPrettyJSONString(ordenPago)).build();
    }
    
    @GET
    @Path("listado/{idSucursal}/{moneda}")
    @Produces("application/json")
    public Response listar(@PathParam("idSucursal") String idSucursal, @PathParam("moneda") String moneda) {
        List<OrdenPago> listaOrdenPago = ordenPagoDao.listar(idSucursal, moneda);
        return Response.ok().entity(printPrettyJSONString(listaOrdenPago)).build();
    }
    
}
