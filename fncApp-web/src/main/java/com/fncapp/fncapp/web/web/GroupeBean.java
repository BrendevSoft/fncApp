/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.Constante;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author NOAMESSI
 */
@Named(value = "profilBean")
@ViewScoped
public class GroupeBean implements Serializable {

    private Groupe profil;
    private List<Groupe> profils;
    @EJB
    private GroupeServiceBeanLocal psbl;

    /**
     * Creates a new instance of CategoriePersonnelBean
     */
    public GroupeBean() {
        this.profils = new ArrayList<>();
        this.profil = new Groupe();
    }

    public void cancel(ActionEvent actionEvent) {
        this.profil = new Groupe();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.profil.getId() == null) {
                this.psbl.saveOne(profil);
                context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            } else {
                this.psbl.updateOne(profil);
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.profil = new Groupe();

        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
        }

    }

    public void getObject(Long id) {
        this.profil = this.psbl.find(id);
    }

    public List<Groupe> getProfils() {
        profils = this.psbl.getAll();
        return profils;
    }

    public void setProfils(List<Groupe> profils) {
        this.profils = profils;
    }

    public Groupe getProfil() {
        return profil;
    }

    public void setProfil(Groupe profil) {
        this.profil = profil;
    }

    public GroupeServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(GroupeServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

}
