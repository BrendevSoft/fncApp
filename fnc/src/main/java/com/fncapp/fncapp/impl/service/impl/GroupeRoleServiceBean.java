/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.service.impl;

import com.fncapp.fncapp.api.dao.GroupeRoleDaoBeanLocal;
import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeRoleId;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.service.GroupeRoleServiceBeanLocal;
import com.fncapp.fncapp.impl.service.core.impl.BaseServiceBean;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeRoleServiceBean extends BaseServiceBean<GroupeRole, GroupeRoleId> implements GroupeRoleServiceBeanLocal {

    @EJB
    private GroupeRoleDaoBeanLocal grdbl;

    @Override
    public BaseDaoBeanLocal<GroupeRole, GroupeRoleId> getDao() {
        return grdbl;
    }

    @Override
    public List<Rolee> getProfilRoles(Groupe groupe) {
        return this.grdbl.getGroupeRoles(groupe);
    }

    @Override
    public GroupeRole getGroupeRoles(Groupe groupe, Rolee role) {
        return this.grdbl.getGroupeRoles(groupe, role);
    }

    @Override
    public boolean supGroupeRoles(GroupeRole cRole) {
        return this.grdbl.supGroupeRoles(cRole);
    }
}
