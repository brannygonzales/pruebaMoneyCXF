/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.dao;

import com.avantica.serviciosparte2.entity.Banco;
import java.util.List;

/**
 *
 * @author branlap
 * @param <T>
 */
public interface SucursalDao<T> {
    
    void crear(T t);
    
    void actualizar(T t);
    
    void eliminar(Long id);
    
    List<T> listar();
    
    T buscar(Long id);
    
    List<T> buscar(Banco banco);
    
}
