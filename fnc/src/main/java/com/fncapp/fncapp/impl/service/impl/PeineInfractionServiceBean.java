/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.PeineInfractionDaoBeanlocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.PeineInfraction;
import com.fncapp.fncapp.api.entities.PeineInfractionId;
import com.fncapp.fncapp.api.service.PeineInfractionServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class PeineInfractionServiceBean extends BaseServiceBean<PeineInfraction, PeineInfractionId> implements PeineInfractionServiceBeanLocal {

    @EJB
    private PeineInfractionDaoBeanlocal pidb;

    @Override
    public BaseDaoBeanLocal<PeineInfraction, PeineInfractionId> getDao() {
        return pidb;
    }
}
