/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.GroupeDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeServiceBean extends BaseServiceBean<Groupe, Long> implements GroupeServiceBeanLocal {

    @EJB
    private GroupeDaoBeanLocal gdbl;

    @Override
    public BaseDaoBeanLocal<Groupe, Long> getDao() {
        return gdbl;
    }
}
