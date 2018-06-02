/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.service;

import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeRoleId;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.service.core.BaseServiceBeanLocal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Brendev
 */
@Local
public interface GroupeRoleServiceBeanLocal extends BaseServiceBeanLocal<GroupeRole, GroupeRoleId> {

    public List<Rolee> getProfilRoles(Groupe groupe);

    public GroupeRole getGroupeRoles(Groupe groupe, Rolee role);

    public boolean supGroupeRoles(GroupeRole cRole);
}
