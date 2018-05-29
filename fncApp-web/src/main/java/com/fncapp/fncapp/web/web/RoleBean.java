/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.GroupeRole;
import com.fncapp.fncapp.api.entities.GroupeUtilisateur;
import com.fncapp.fncapp.api.entities.Rolee;
import com.fncapp.fncapp.api.service.GroupeRoleServiceBeanLocal;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import com.fncapp.fncapp.api.service.GroupeUtilisateurServiceBeanLocal;
import com.fncapp.fncapp.api.service.RoleServiceBeanLocal;
import com.fncapp.fncapp.impl.shiro.EntityRealm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.annotation.PostConstruct;

/**
 *
 * @author NOAMESSI
 */
@Named(value = "roleBean")
@ViewScoped
public class RoleBean implements Serializable {

    private Rolee role;
    private List<Rolee> roles;
    private List<Rolee> selectRoles;
    private List<Rolee> ajoutRoles;
    private List<Rolee> retraitRoles;
    private List<Groupe> profils;
    private Groupe selectProfil;
    private List<Rolee> mesRoles;
    private String profil;
    private List<GroupeUtilisateur> profilUtilisateurs;
    @EJB
    private GroupeServiceBeanLocal psbl;
    @EJB
    private RoleServiceBeanLocal rsl;
    @EJB
    private GroupeRoleServiceBeanLocal prsbl;
    @EJB
    protected GroupeUtilisateurServiceBeanLocal pudbl;

    /**
     * Creates a new instance of RoleBean
     */
    public RoleBean() {
        role = new Rolee();
        ajoutRoles = new ArrayList<>();
        retraitRoles = new ArrayList<>();
        roles = new ArrayList<>();
        selectRoles = new ArrayList<>();
        selectProfil = new Groupe();
        profils = new ArrayList<>();
        mesRoles = new ArrayList<>();
        this.profilUtilisateurs = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        if (EntityRealm.getUser() != null) {
            this.profilUtilisateurs = this.pudbl.getBy("utilisateur", EntityRealm.getUser());
            if (!profilUtilisateurs.isEmpty()) {
                for (GroupeUtilisateur pu : profilUtilisateurs) {
                    if (pu.getDateRevocation() == null) {
                        profil = pu.getGroupe().getNom();
                        List< GroupeRole> l = this.prsbl.getBy("profil", pu.getGroupe());
                        for (GroupeRole p : l) {
                            mesRoles.add(p.getRole());
                        }
                    }
                }

            }

        }
    }

    public void setProfilRole() {
        selectRoles = this.prsbl.getProfilRoles(selectProfil);
    }

    public void modifierRole() {
        if (selectRoles != null) {
            //recherche des role des profil des personnels
            List<Rolee> profilRoles = prsbl.getProfilRoles(selectProfil);
            //si la liste des roles est vide il s'agit d'une insertion
            if (profilRoles.isEmpty() && !selectRoles.isEmpty()) {
                this.ajoutRolesPers(selectRoles);
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));

                return;
            }
            //si la liste des personne n'est pas vide et ls roles selectioné ne le sont pas on fait une suppression
            if (!profilRoles.isEmpty() && selectRoles.isEmpty()) {
                List<GroupeRole> profilRole = prsbl.getBy("profil", selectProfil);
                for (GroupeRole role2 : profilRole) {
                    prsbl.supGroupeRoles(role2);
                }
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));
                return;
            }
            //si la liste des role n'est pas vide et la liste des roles selectionné nest pas vide
            if (!profilRoles.isEmpty() && !selectRoles.isEmpty()) {
                //chercher les role que le personnel a retirer
                for (Rolee roleProfil : profilRoles) {
                    if (!selectRoles.contains(roleProfil)) {
                        retraitRoles.add(roleProfil);
                    }
                }
                //chercher les role a ajouter
                for (Rolee roleSelect : selectRoles) {
                    if (!profilRoles.contains(roleSelect)) {
                        ajoutRoles.add(roleSelect);
                    }
                }
                //ajout des roles
                if (!ajoutRoles.isEmpty()) {
                    ajoutRolesPers(ajoutRoles);
                }
                //retrait de role
                if (!retraitRoles.isEmpty()) {
                    for (Rolee catPersonneRole : retraitRoles) {
                        System.out.println(catPersonneRole);
                    }
                    for (Rolee role1 : retraitRoles) {
                        GroupeRole profilRol = prsbl.getGroupeRoles(selectProfil, role1);
                        System.out.println(profilRol);
                        if (profilRol != null) {
                            prsbl.supGroupeRoles(profilRol);
                        }
                    }
                }
                FacesContext.getCurrentInstance().
                        addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info Modification réussit !!!!!!!!", ""));
            }
        }
    }

    public void ajoutRolesPers(List<Rolee> roles) {
        for (Rolee role1 : roles) {
            GroupeRole profilRole = new GroupeRole(selectProfil, role1);
            prsbl.saveOne(profilRole);
        }
    }

    public void saveRole() {
        this.rsl.saveOne(role);
    }

    public Rolee getRole() {
        return role;
    }

    public void setRole(Rolee role) {
        this.role = role;
    }

    public List<Rolee> getRoles() {
        roles = this.rsl.getAll();
        return roles;
    }

    public void setRoles(List<Rolee> roles) {
        this.roles = roles;
    }

    public List<Rolee> getSelectRoles() {
        return selectRoles;
    }

    public void setSelectRoles(List<Rolee> selectRoles) {
        this.selectRoles = selectRoles;
    }

    public List<Rolee> getAjoutRoles() {
        return ajoutRoles;
    }

    public void setAjoutRoles(List<Rolee> ajoutRoles) {
        this.ajoutRoles = ajoutRoles;
    }

    public List<Rolee> getRetraitRoles() {
        return retraitRoles;
    }

    public void setRetraitRoles(List<Rolee> retraitRoles) {
        this.retraitRoles = retraitRoles;
    }

    public List<Groupe> getProfils() {
        profils = this.psbl.getAll();
        return profils;
    }

    public void setProfils(List<Groupe> profils) {
        this.profils = profils;
    }

    public RoleServiceBeanLocal getRsl() {
        return rsl;
    }

    public void setRsl(RoleServiceBeanLocal rsl) {
        this.rsl = rsl;
    }

    public List<Rolee> getMesRoles() {
        return mesRoles;
    }

    public void setMesRoles(List<Rolee> mesRoles) {
        this.mesRoles = mesRoles;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public Groupe getSelectProfil() {
        return selectProfil;
    }

    public void setSelectProfil(Groupe selectProfil) {
        this.selectProfil = selectProfil;
    }

    public List<GroupeUtilisateur> getProfilUtilisateurs() {
        return profilUtilisateurs;
    }

    public void setProfilUtilisateurs(List<GroupeUtilisateur> profilUtilisateurs) {
        this.profilUtilisateurs = profilUtilisateurs;
    }

    public GroupeServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(GroupeServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

    public GroupeRoleServiceBeanLocal getPrsbl() {
        return prsbl;
    }

    public void setPrsbl(GroupeRoleServiceBeanLocal prsbl) {
        this.prsbl = prsbl;
    }

    public GroupeUtilisateurServiceBeanLocal getPudbl() {
        return pudbl;
    }

    public void setPudbl(GroupeUtilisateurServiceBeanLocal pudbl) {
        this.pudbl = pudbl;
    }

}
