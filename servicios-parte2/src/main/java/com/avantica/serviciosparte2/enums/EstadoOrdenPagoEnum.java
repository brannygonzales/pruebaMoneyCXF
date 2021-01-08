/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.enums;

/**
 *
 * @author branlap
 */
public enum EstadoOrdenPagoEnum {
    
    PAGADA,DECLINADA,FALLIDA,ANULADA;
    
    public static String obtenerEstadoOrdenPagoEnumName(String enumName) {
        return EstadoOrdenPagoEnum.valueOf(enumName).name();
    }
    
}
