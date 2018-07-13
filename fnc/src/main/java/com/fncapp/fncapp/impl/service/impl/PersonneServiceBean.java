/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.PersonneDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Personne;
import com.fncapp.fncapp.api.service.PersonneServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class PersonneServiceBean extends BaseServiceBean<Personne, Long> implements PersonneServiceBeanLocal{

    @EJB
    private PersonneDaoBeanLocal pdbl;
    
    @Override
    protected BaseDaoBeanLocal<Personne, Long> getDao(){
        return pdbl;
    }

}
