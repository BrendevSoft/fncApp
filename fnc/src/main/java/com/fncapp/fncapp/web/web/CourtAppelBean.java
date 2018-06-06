/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.impl.shiro.Constante;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.CourtAppel;
import com.fncapp.fncapp.api.service.CourtAppelServiceBeanlocal;
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
@Named(value = "courtAppelBean")
@ViewScoped
public class CourtAppelBean implements Serializable {

    private MethodeJournalisation journalisation;
    private CourtAppel courtAppel;
    private List<CourtAppel> courtAppels;
    private List<CourtAppel> courtAppelsFilter;

    @EJB
    private CourtAppelServiceBeanlocal casb;

    /**
     * Creates a new instance of CourtAppelBean
     */
    public CourtAppelBean() {
        this.courtAppel = new CourtAppel();
        this.courtAppels = new ArrayList<>();
        this.journalisation = new MethodeJournalisation();
    }

    public void cancel(ActionEvent actionEvent) {
        this.courtAppel = new CourtAppel();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<CourtAppel> courtAppel1 = this.casb.getBy("libelle", this.courtAppel.getLibelle());
            if (this.courtAppel.getId() == null) {
                if (courtAppel1.isEmpty()) {
                    this.courtAppel.setDatecreation(new Date());
                    this.courtAppel.setRowvers(new Date());
                    this.casb.saveOne(courtAppel);
                    journalisation.saveLog4j(CourtAppelBean.class.getName(), Level.INFO, "Enregistrement d'une Court d'Appel :" + courtAppel.getLibelle());
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));

                } else {
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE + ", Le libellé saisi existe déja dans la base"));

                }
            } else {
                this.courtAppel.setRowvers(new Date());
                this.casb.updateOne(courtAppel);
                journalisation.saveLog4j(CourtAppelBean.class.getName(), Level.INFO, "Modification d'une Court d'Appel :" + courtAppel.getLibelle());

                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.courtAppel = new CourtAppel();
            tx.commit();
        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(CourtAppelBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(CourtAppelBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(CourtAppelBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    public void getObject(Long id) {
        this.courtAppel = this.casb.find(id);
        System.out.println(this.courtAppel.getId());
    }

    public MethodeJournalisation getJournalisation() {
        return journalisation;
    }

    public void setJournalisation(MethodeJournalisation journalisation) {
        this.journalisation = journalisation;
    }

    public CourtAppel getCourtAppel() {
        return courtAppel;
    }

    public void setCourtAppel(CourtAppel courtAppel) {
        this.courtAppel = courtAppel;
    }

    public List<CourtAppel> getCourtAppels() {
        this.courtAppels = this.casb.getAll();
        return courtAppels;
    }

    public void setCourtAppels(List<CourtAppel> courtAppels) {
        this.courtAppels = courtAppels;
    }

    public List<CourtAppel> getCourtAppelsFilter() {
        return courtAppelsFilter;
    }

    public void setCourtAppelsFilter(List<CourtAppel> courtAppelsFilter) {
        this.courtAppelsFilter = courtAppelsFilter;
    }

    public CourtAppelServiceBeanlocal getCasb() {
        return casb;
    }

    public void setCasb(CourtAppelServiceBeanlocal casb) {
        this.casb = casb;
    }

}
