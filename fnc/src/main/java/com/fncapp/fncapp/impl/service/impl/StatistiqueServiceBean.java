/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.StatistiqueDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Statistique;
import com.fncapp.fncapp.api.service.StatistiqueServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class StatistiqueServiceBean extends BaseServiceBean<Statistique, Long> implements StatistiqueServiceBeanLocal {

    @EJB
    private StatistiqueDaoBeanLocal sdbl;

    @Override
    protected BaseDaoBeanLocal<Statistique, Long> getDao() {
        return sdbl;
    }

}
