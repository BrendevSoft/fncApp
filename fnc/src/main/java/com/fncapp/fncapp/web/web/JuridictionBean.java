/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.impl.shiro.Constante;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.dao.CourtAppelDaoBeanLocal;
import com.fncapp.fncapp.api.entities.CourtAppel;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.api.service.JuridictionServiceBeanLocal;
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
@Named(value = "juridictionBean")
@ViewScoped
public class JuridictionBean implements Serializable {

    private Juridiction juridiction;
    private CourtAppel courtAppel;
    private List<Juridiction> juridictions;
    private List<Juridiction> juridictionsFilter;
    private List<CourtAppel> courtAppels;
    private MethodeJournalisation journalisation;

    @EJB
    private JuridictionServiceBeanLocal jsbl;
    @EJB
    private CourtAppelDaoBeanLocal cadbl;

    /**
     * Creates a new instance of JuridictionBean
     */
    public JuridictionBean() {
        this.juridiction = new Juridiction();
        this.juridictions = new ArrayList<>();
        this.courtAppel = new CourtAppel();
        this.courtAppels = new ArrayList<>();
        this.journalisation = new MethodeJournalisation();
    }

    public void cancel(ActionEvent actionEvent) {
        try {
            this.courtAppel = new CourtAppel();
            this.juridiction = new Juridiction();
        } catch (Exception e) {
        }
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            List<Juridiction> juridictions1 = this.jsbl.getBy("libellecourt", this.juridiction.getLibellecourt());
            if (this.juridiction.getId() == null) {
                if (juridictions1.isEmpty()) {
                    this.juridiction.setDatecreation(new Date());
                    this.juridiction.setRowvers(new Date());
                    this.juridiction.setCourtAppel(courtAppel);
                    this.jsbl.saveOne(juridiction);
                    System.out.println(juridiction);
                    journalisation.saveLog4j(CourtAppelBean.class.getName(), Level.INFO, "Enregistrement d'un tribunal :" + juridiction.getLibellecourt());
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));

                } else {
                    context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE + ", Le libellé saisi existe déja dans la base"));
                }
            } else {

                this.juridiction.setRowvers(new Date());
                this.juridiction.setCourtAppel(courtAppel);
                this.jsbl.updateOne(juridiction);
                journalisation.saveLog4j(CourtAppelBean.class.getName(), Level.INFO, "Modification d'un tribunal :" + juridiction.getLibellecourt());
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));

            }
            this.courtAppel = new CourtAppel();
            this.juridiction = new Juridiction();
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
        try {
            this.juridiction = this.jsbl.find(id);
            if (this.juridiction.getCourtAppel() != null) {
                this.courtAppel = this.juridiction.getCourtAppel();
            }
        } catch (Exception e) {
        }
    }

    public Juridiction getJuridiction() {
        return juridiction;

    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public List<Juridiction> getJuridictions() {
        try {
            this.juridictions = this.jsbl.getAll("libellecourt",true);
        } catch (Exception e) {
        }
        return juridictions;
    }

    public void setJuridictions(List<Juridiction> juridictions) {
        this.juridictions = juridictions;
    }

    public List<Juridiction> getJuridictionsFilter() {
        return juridictionsFilter;
    }

    public void setJuridictionsFilter(List<Juridiction> juridictionsFilter) {
        this.juridictionsFilter = juridictionsFilter;
    }

    public JuridictionServiceBeanLocal getJsbl() {
        return jsbl;
    }

    public void setJsbl(JuridictionServiceBeanLocal jsbl) {
        this.jsbl = jsbl;
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
        try {
            this.courtAppels = this.cadbl.getAll("libelle",true);
        } catch (Exception e) {
        }
        return courtAppels;
    }

    public void setCourtAppels(List<CourtAppel> courtAppels) {
        this.courtAppels = courtAppels;
    }

    public CourtAppelDaoBeanLocal getCadbl() {
        return cadbl;
    }

    public void setCadbl(CourtAppelDaoBeanLocal cadbl) {
        this.cadbl = cadbl;
    }

}
