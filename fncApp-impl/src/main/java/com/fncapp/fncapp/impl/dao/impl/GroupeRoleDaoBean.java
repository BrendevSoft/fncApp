/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.impl.dao.impl;

import com.fncapp.fncapp.api.dao.GroupeRoleDaoBeanLocal;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeRoleId;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.impl.dao.core.impl.BaseDaoBean;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Brendev
 */
@Stateless
public class GroupeRoleDaoBean extends BaseDaoBean<GroupeRole, GroupeRoleId> implements GroupeRoleDaoBeanLocal {

    public GroupeRoleDaoBean() {
    }

    @Override
    public Class<GroupeRole> getType() {
        return GroupeRole.class;
    }

    @Override
    public List<Rolee> getGroupeRoles(Groupe groupe) {
        return getEntityManager()
                .createQuery("SELECT p.role FROM GroupeRole p "
                        + "WHERE p.groupe =:groupe")
                .setParameter("groupe", groupe)
                .getResultList();
    }

    @Override
    public GroupeRole getGroupeRoles(Groupe groupe, Rolee role) {
        return (GroupeRole) getEntityManager()
                .createQuery("SELECT p FROM GroupeRole p "
                        + "WHERE p.groupe=:groupe AND p.role=:role")
                .setParameter("groupe", groupe)
                .setParameter("role", role)
                .getSingleResult();
    }

    @Override
    public boolean supGroupeRoles(GroupeRole cRole) {
        try {
            em.createQuery("DELETE FROM GroupeRole pr WHERE pr.groupe=:groupe AND pr.role=:role")
                    .setParameter("groupe", cRole.getGroupe())
                    .setParameter("role", cRole.getRole())
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
