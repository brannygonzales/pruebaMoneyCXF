/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author branlap
 */
@Entity
public class OrdenPago implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double monto;
    
    private String moneda;
    
    private String idSucursal;
    
    private String estado;
    
    @Temporal(TemporalType.DATE)
    private Date fechaPago;
    
    private String idSucursalPago;

    public OrdenPago() {
    }

    public OrdenPago(Double monto, String moneda, String idSucursal) {
        this.monto = monto;
        this.moneda = moneda;
        this.idSucursal = idSucursal;
    }

    public OrdenPago(Long id, String estado, Date fechaPago, String idSucursalPago) {
        this.id = id;
        this.estado = estado;
        this.fechaPago = fechaPago;
        this.idSucursalPago = idSucursalPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getIdSucursalPago() {
        return idSucursalPago;
    }

    public void setIdSucursalPago(String idSucursalPago) {
        this.idSucursalPago = idSucursalPago;
    }
    
}
