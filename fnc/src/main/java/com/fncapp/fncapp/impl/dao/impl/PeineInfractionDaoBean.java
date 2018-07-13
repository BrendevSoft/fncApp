/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.PeineInfractionDaoBeanlocal;
import com.fncapp.fncapp.api.entities.PeineInfraction;
import com.fncapp.fncapp.api.entities.PeineInfractionId;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class PeineInfractionDaoBean extends BaseDaoBean<PeineInfraction, PeineInfractionId> implements PeineInfractionDaoBeanlocal {

    public PeineInfractionDaoBean() {
    }

    @Override
    public Class<PeineInfraction> getType() {
        return PeineInfraction.class;
    }
}
