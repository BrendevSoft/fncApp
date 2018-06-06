/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.impl.shiro.Constante;
import com.fncapp.fncapp.api.api.utils.ManipulationDate;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.service.GroupeServiceBeanLocal;
import com.fncapp.fncapp.impl.transaction.TransactionManager;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author NOAMESSI
 */
@Named(value = "profilBean")
@ViewScoped
public class GroupeBean implements Serializable {

    private Groupe profil;
    private List<Groupe> profils;
    private List<Groupe> profilsFilter;
    private MethodeJournalisation journalisation;
    @EJB
    private GroupeServiceBeanLocal psbl;

    /**
     * Creates a new instance of CategoriePersonnelBean
     */
    public GroupeBean() {
        this.profils = new ArrayList<>();
        this.profil = new Groupe();
        this.journalisation = new MethodeJournalisation();
    }

    public void cancel(ActionEvent actionEvent) {
        this.profil = new Groupe();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<Groupe> groupes1 = this.psbl.getBy("nom", this.profil.getNom());
            if (this.profil.getId() == null) {
                if (groupes1.isEmpty()) {
                    this.profil.setDatecreation(new Date());
                    this.psbl.saveOne(profil);
                    journalisation.saveLog4j(GroupeBean.class.getName(), Level.INFO, "Enregistrement d'un profil :" + profil.getNom());
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
                } else {
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE + ", Le libellé saisi existe déja dans la base"));
                }
            } else {
                this.psbl.updateOne(profil);
                journalisation.saveLog4j(GroupeBean.class.getName(), Level.INFO, "Modification d'un profil :" + profil.getNom());

                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.profil = new Groupe();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(GroupeBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(GroupeBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(GroupeBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    public void getObject(Long id) {
        System.out.println(this.psbl.find(id));
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

    public List<Groupe> getProfilsFilter() {
        return profilsFilter;
    }

    public void setProfilsFilter(List<Groupe> profilsFilter) {
        this.profilsFilter = profilsFilter;
    }

}
