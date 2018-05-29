/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.RoleDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class RoleDaoBean extends BaseDaoBean<Rolee, Long> implements RoleDaoBeanLocal {

    public RoleDaoBean() {
    }

    @Override
    public Class<Rolee> getType() {
        return Rolee.class;
    }
}
