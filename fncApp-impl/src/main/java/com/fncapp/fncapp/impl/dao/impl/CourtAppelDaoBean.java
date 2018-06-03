/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.CourtAppelDaoBeanLocal;
import com.fncapp.fncapp.api.entities.CourtAppel;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class CourtAppelDaoBean extends BaseDaoBean<CourtAppel, Long> implements CourtAppelDaoBeanLocal {

    public CourtAppelDaoBean() {
    }

    @Override
    public Class<CourtAppel> getType() {
        return CourtAppel.class;
    }
}
