/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.ServiceDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Service;
import com.fncapp.fncapp.api.service.ServiceServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class ServiceServiceBean extends BaseServiceBean<Service, Long> implements ServiceServiceBeanLocal{

    @EJB
    private ServiceDaoBeanLocal sdbl;
    
    @Override
    protected BaseDaoBeanLocal<Service, Long> getDao(){
        return sdbl;
    }

}
