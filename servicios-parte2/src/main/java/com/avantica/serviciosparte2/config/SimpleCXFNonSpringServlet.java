/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.avantica.serviciosparte2.config;

import com.avantica.serviciosparte2.ws.SucursalWSImpl;
import javax.servlet.ServletConfig;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

/**
 *
 * @author branlap
 */
public class SimpleCXFNonSpringServlet extends CXFNonSpringServlet {

    @Override
    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);
        ServerFactoryBean factory = new ServerFactoryBean();
        factory.setBus(bus);
        factory.setServiceClass(SucursalWSImpl.class);
        factory.setAddress("/SucursalWS");
        factory.create();
    }
    
}
