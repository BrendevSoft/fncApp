/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.CorpsDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Corps;
import com.fncapp.fncapp.api.service.CorpsServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class CorpsServiceBean extends BaseServiceBean<Corps, Long> implements CorpsServiceBeanLocal{

    @EJB
    private CorpsDaoBeanLocal cdbl;
    
    @Override
    protected BaseDaoBeanLocal<Corps, Long> getDao(){
        return cdbl;
    }

}

