/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.RoleDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.service.RoleServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class RoleServiceBean extends BaseServiceBean<Rolee, Long> implements RoleServiceBeanLocal {

    @EJB
    private RoleDaoBeanLocal rdbl;

    @Override
    public BaseDaoBeanLocal<Rolee, Long> getDao() {
        return rdbl;
    }
}
