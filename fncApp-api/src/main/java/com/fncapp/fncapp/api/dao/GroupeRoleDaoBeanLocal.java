/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.dao;

import com.fncapp.fncapp.api.dao.core.BaseDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeRoleId;
import com.fncapp.fncapp.api.entities.Rolee;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Brendev
 */
@Remote
public interface GroupeRoleDaoBeanLocal extends BaseDaoBeanLocal<GroupeRole, GroupeRoleId> {

    public List<Rolee> getGroupeRoles(Groupe groupe);

    public GroupeRole getGroupeRoles(Groupe groupe, Rolee role);

    public boolean supGroupeRoles(GroupeRole cRole);
}
