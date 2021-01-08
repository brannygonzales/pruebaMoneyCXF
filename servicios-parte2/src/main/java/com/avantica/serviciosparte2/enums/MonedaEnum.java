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
public enum MonedaEnum {
    
    PEN,USD;
    
    public static String obtenerMonedaEnumName(String enumName) {
        return MonedaEnum.valueOf(enumName).name();
    }
    
}
