/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.StatistiqueDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Statistique;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edson PAKOU
 */
@Stateless
public class StatistiqueDaoBean extends BaseDaoBean<Statistique, Long> implements StatistiqueDaoBeanLocal {

    public StatistiqueDaoBean() {
    }

    @Override
    public Class<Statistique> getType() {
        return Statistique.class;
    }
}
