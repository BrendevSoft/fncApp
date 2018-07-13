/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.CourtAppelDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.CourtAppel;
import com.fncapp.fncapp.api.service.CourtAppelServiceBeanlocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class CourtAppelServiceBean extends BaseServiceBean<CourtAppel, Long> implements CourtAppelServiceBeanlocal {

    @EJB
    private CourtAppelDaoBeanLocal cadbl;

    @Override
    public BaseDaoBeanLocal<CourtAppel, Long> getDao() {
        return cadbl;
    }
}
