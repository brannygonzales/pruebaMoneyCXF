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
public class ResponseBase {
    
    private boolean exito;

    public ResponseBase() {
    }

    public ResponseBase(boolean exito) {
        this.exito = exito;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }
    
}
