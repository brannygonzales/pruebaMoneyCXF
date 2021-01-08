/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.dao.impl;

import com.avantica.serviciosparte2.crud.CrudJpa;
import com.avantica.serviciosparte2.dao.BancoDao;
import com.avantica.serviciosparte2.entity.Banco;
import java.util.List;

/**
 *
 * @author branlap
 */
public class BancoDaoJpa extends CrudJpa<Banco> implements BancoDao {

    public BancoDaoJpa(Class<Banco> objeto) {
        super(objeto);
    }
    
}
