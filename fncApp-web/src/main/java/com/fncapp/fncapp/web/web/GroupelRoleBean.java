/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeRoleId;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.entities.Utilisateur;
import com.fncapp.fncapp.api.service.GroupeRoleServiceBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Brendev
 */
@Named(value = "profilRoleBean")
@RequestScoped
public class GroupelRoleBean {

    private GroupeRole personnelRole;
    private List<GroupeRole> personnelRoles;
    private Rolee role;
    private Utilisateur utilisateur;

    @EJB
    private GroupeRoleServiceBeanLocal prsbl;

    public GroupelRoleBean() {
        this.utilisateur = new Utilisateur();
        this.role = new Rolee();
        this.personnelRole = new GroupeRole();
    }

    public void getObject(GroupeRoleId id) {
        this.prsbl.find(id);
    }

    public List<GroupeRole> getPersonnelRoles() {
        personnelRoles = new ArrayList<>();
        personnelRoles = this.prsbl.getAll();
        return personnelRoles;
    }

    public void setPersonnelRoles(List<GroupeRole> personnelRoles) {
        this.personnelRoles = personnelRoles;
    }

    public Rolee getRole() {
        return role;
    }

    public void setRole(Rolee role) {
        this.role = role;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public GroupeRole getPersonnelRole() {
        return personnelRole;
    }

    public void setPersonnelRole(GroupeRole personnelRole) {
        this.personnelRole = personnelRole;
    }

    public GroupeRoleServiceBeanLocal getPrsbl() {
        return prsbl;
    }

    public void setPrsbl(GroupeRoleServiceBeanLocal prsbl) {
        this.prsbl = prsbl;
    }

}
