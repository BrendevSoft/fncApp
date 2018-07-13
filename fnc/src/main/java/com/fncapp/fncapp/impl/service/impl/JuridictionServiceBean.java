/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.JuridictionDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.api.service.JuridictionServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class JuridictionServiceBean extends BaseServiceBean<Juridiction, Long> implements JuridictionServiceBeanLocal{

    @EJB
    private JuridictionDaoBeanLocal jdbl;
    
    @Override
    protected BaseDaoBeanLocal<Juridiction, Long> getDao(){
        return jdbl;
    }

}
