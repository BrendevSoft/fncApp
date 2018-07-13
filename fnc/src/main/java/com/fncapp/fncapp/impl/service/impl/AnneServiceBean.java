/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.AnneDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Annee;
import com.fncapp.fncapp.api.service.AnneServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class AnneServiceBean extends BaseServiceBean<Annee, Long> implements AnneServiceBeanLocal {

    @EJB
    private AnneDaoBeanLocal adbl;
    
    @Override
    protected BaseDaoBeanLocal<Annee, Long> getDao(){
        return adbl;
    }

}
