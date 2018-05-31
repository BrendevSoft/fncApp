/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.PrisonDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Prison;
import com.fncapp.fncapp.api.service.PrisonServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class PrisonServiceBean extends BaseServiceBean<Prison, Long> implements PrisonServiceBeanLocal{

   @EJB
    private PrisonDaoBeanLocal pdbl;
    
    @Override
    protected BaseDaoBeanLocal<Prison, Long> getDao(){
        return pdbl;
    }

}
