/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.CondamnationDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Condamnation;
import com.fncapp.fncapp.api.service.CondamnationServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CondamnationServiceBean extends BaseServiceBean<Condamnation, Long> implements CondamnationServiceBeanLocal{

   @EJB
    private CondamnationDaoBeanLocal cdbl;
    
    @Override
    protected BaseDaoBeanLocal<Condamnation, Long> getDao(){
        return cdbl;
    }

}
