/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.GroupeDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeDaoBean extends BaseDaoBean<Groupe, Long> implements GroupeDaoBeanLocal {

    public GroupeDaoBean() {
    }

    @Override
    public Class<Groupe> getType() {
        return Groupe.class;
    }
}
