/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.bean;

/**
 *
 * @author branlap
 */
public class OrdenPagoCambioRequestBean {
    
    private String estado;
    private String fechaPago;
    private String idSucursalPago;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getIdSucursalPago() {
        return idSucursalPago;
    }

    public void setIdSucursalPago(String idSucursalPago) {
        this.idSucursalPago = idSucursalPago;
    }
    
}
