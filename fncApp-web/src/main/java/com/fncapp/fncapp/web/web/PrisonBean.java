/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.impl.shiro.Constante;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.Prison;
import com.fncapp.fncapp.api.service.PrisonServiceBeanLocal;
import com.fncapp.fncapp.impl.transaction.TransactionManager;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author Brendev
 */
@Named(value = "prisonBean")
@ViewScoped
public class PrisonBean implements Serializable {

    private Prison prison;
    private List<Prison> prisons;
    private List<Prison> prisonsFilter;
    private MethodeJournalisation journalisation;

    @EJB
    private PrisonServiceBeanLocal psbl;

    /**
     * Creates a new instance of PrisonBean
     */
    public PrisonBean() {
        this.prison = new Prison();
        this.prisons = new ArrayList<>();
        this.journalisation = new MethodeJournalisation();
    }

    public void cancel(ActionEvent actionEvent) {
        this.prison = new Prison();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<Prison> prisons1 = this.psbl.getBy("libellecourt", this.prison.getLibellecourt());

            if (this.prison.getId() == null) {
                if (prisons1.isEmpty()) {
                    this.prison.setRowvers(new Date());
                    this.prison.setDatecreation(new Date());
                    this.psbl.saveOne(prison);
                    journalisation.saveLog4j(PrisonBean.class.getName(), Level.INFO, "Enregistrement d'une prison :" + prison.getLibellecourt());
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
                } else {
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE + ", Le libellé saisi existe déja dans la base"));
                }

            } else {
                this.prison.setRowvers(new Date());
                this.psbl.updateOne(prison);
                journalisation.saveLog4j(PrisonBean.class.getName(), Level.INFO, "Modification d'une prison :" + prison.getLibellecourt());
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.prison = new Prison();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(PrisonBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(PrisonBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(PrisonBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    public void getObject(Long id) {
        this.prison = this.psbl.find(id);
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public List<Prison> getPrisons() {
        this.prisons = this.psbl.getAll();
        return prisons;
    }

    public void setPrisons(List<Prison> prisons) {
        this.prisons = prisons;
    }

    public List<Prison> getPrisonsFilter() {
        return prisonsFilter;
    }

    public void setPrisonsFilter(List<Prison> prisonsFilter) {
        this.prisonsFilter = prisonsFilter;
    }

    public PrisonServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(PrisonServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

}
