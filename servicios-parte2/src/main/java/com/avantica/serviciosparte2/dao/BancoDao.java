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
 */
public interface BancoDao {
    
    void crear(Banco banco);
    
    void actualizar(Banco banco);
    
    void eliminar(Long id);
    
    List<Banco> listar();
    
    Banco buscar(Long id);
    
}
