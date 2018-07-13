/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.AnneDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Annee;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class AnneDaoBean extends BaseDaoBean<Annee, Long> implements AnneDaoBeanLocal {

    public AnneDaoBean() {
    }

    @Override
    public Class<Annee> getType() {
        return Annee.class;
    }

}
