/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.Constante;
import com.fncapp.fncapp.api.api.utils.ManipulationDate;
import com.fncapp.fncapp.api.api.utils.MethodeJournalisation;
import com.fncapp.fncapp.api.entities.Annee;
import com.fncapp.fncapp.api.entities.Condamnation;
import com.fncapp.fncapp.api.entities.Infraction;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.api.entities.Peine;
import com.fncapp.fncapp.api.entities.Personne;
import com.fncapp.fncapp.api.entities.Prison;
import com.fncapp.fncapp.api.entities.Situation;
import com.fncapp.fncapp.api.entities.Statistique;
import com.fncapp.fncapp.api.service.AnneServiceBeanLocal;
import com.fncapp.fncapp.api.service.CondamnationServiceBeanLocal;
import com.fncapp.fncapp.api.service.InfractionServiceBeanLocal;
import com.fncapp.fncapp.api.service.JuridictionServiceBeanLocal;
import com.fncapp.fncapp.api.service.PeineServiceBeanLocal;
import com.fncapp.fncapp.api.service.PersonneServiceBeanLocal;
import com.fncapp.fncapp.api.service.PrisonServiceBeanLocal;
import com.fncapp.fncapp.api.service.SituationServiceBeanLocal;
import com.fncapp.fncapp.api.service.StatistiqueServiceBeanLocal;
import com.fncapp.fncapp.impl.transaction.TransactionManager;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
@Named(value = "condamnationBean")
@ViewScoped
public class CondamnationBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private MethodeJournalisation journalisation;
    private Condamnation condamnation;
    private Personne personne;
    private Juridiction juridiction;
    private Situation situation;
    private Infraction infraction;
    private Prison prison;
    private Peine peine;
    private Annee annee;
    private Statistique statistique;
    private String typeSituation = "";
    private Boolean det = false;
    private Boolean lib = false;
    private Boolean fuit = false;

    private List<Condamnation> condamnations;
    private List<Condamnation> condamnationsFilter;
    private List<Personne> personnes;
    private List<Juridiction> juridictions;
    private List<Situation> situations;
    private List<Situation> situationsFileter;
    private List<Infraction> infractions;
    private List<Prison> prisons;
    private List<Peine> peines;
    private List<Annee> annees;

    @EJB
    private CondamnationServiceBeanLocal csbl;
    @EJB
    private PersonneServiceBeanLocal psbl;
    @EJB
    private JuridictionServiceBeanLocal jsbl;
    @EJB
    private SituationServiceBeanLocal ssbl;
    @EJB
    private InfractionServiceBeanLocal isbl;
    @EJB
    private PrisonServiceBeanLocal psbl1;
    @EJB
    private PeineServiceBeanLocal psbl2;
    @EJB
    private AnneServiceBeanLocal asbl;
    @EJB
    private StatistiqueServiceBeanLocal ssbl1;

    /**
     * Creates a new instance of CondamnationBean
     */
    public CondamnationBean() {
        this.condamnation = new Condamnation();
        this.condamnations = new ArrayList<>();

        this.juridiction = new Juridiction();
        this.juridictions = new ArrayList<>();

        this.personne = new Personne();
        this.personnes = new ArrayList<>();

        this.situation = new Situation();
        this.situations = new ArrayList<>();

        this.infraction = new Infraction();
        this.infractions = new ArrayList<>();

        this.prison = new Prison();
        this.prisons = new ArrayList<>();

        this.peine = new Peine();
        this.peines = new ArrayList<>();

        this.annee = new Annee();
        this.annees = new ArrayList<>();

        this.statistique = new Statistique();

        this.journalisation = new MethodeJournalisation();
    }

    @PostConstruct
    public void init() {
        visible();
    }

    public void cancel(ActionEvent actionEvent) {
        this.condamnation = new Condamnation();
        this.juridiction = new Juridiction();
        this.personne = new Personne();
        this.situation = new Situation();
        this.infraction = new Infraction();
        this.prison = new Prison();
        this.peine = new Peine();
        this.statistique = new Statistique();
    }

    public void visible() {
        this.det = false;
        this.lib = false;
        this.fuit = false;

        if (this.typeSituation.equals("En Détention")) {
            this.det = true;
            this.lib = false;
            this.fuit = false;
        } else if (this.typeSituation.equals("En liberté")) {
            this.det = false;
            this.lib = true;
            this.fuit = false;
        } else if (this.typeSituation.equals("En Fuite")) {
            this.det = false;
            this.lib = false;
            this.fuit = true;
        } else {
            this.det = false;
            this.lib = false;
            this.fuit = false;
        }
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        UserTransaction tx = TransactionManager.getUserTransaction();
        try {
            tx.begin();
            Annee anneeCondam = new Annee();

            this.personne.setDatecreation(new Date());
            this.personne.setRowvers(new Date());
            this.psbl.saveOne(personne);
            journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une personne :" + this.personne.getNom().concat(this.personne.getPrenom()));
            this.journalisation = new MethodeJournalisation();

            //this.peine.setInfraction(infraction);
            this.peine.setDatecreation(new Date());
            this.peine.setRowvers(new Date());
            this.psbl2.saveOne(peine);
            journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une peine :" + this.peine.getPeineIs());
            this.journalisation = new MethodeJournalisation();

            List<Annee> annee1 = this.asbl.getBy("code", String.valueOf(ManipulationDate.RecupererAnnee(this.condamnation.getDatejugement())));
            if (annee1.isEmpty()) {
                this.annee.setCode(String.valueOf(ManipulationDate.RecupererAnnee(this.condamnation.getDatejugement())));
                this.annee.setDatecreation(new Date());
                this.annee.setRowvers(new Date());
                anneeCondam = this.asbl.saveOne(annee);
                journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une année :" + this.annee.getCode());
                this.journalisation = new MethodeJournalisation();
            } else {
                anneeCondam = annee1.get(0);
            }

            this.condamnation.setDatecreation(new Date());
            this.condamnation.setRowvers(new Date());
            this.condamnation.setJuridiction(juridiction);
            this.condamnation.setPeine(peine);
            this.condamnation.setPersonne(personne);
            this.condamnation.setAnnee(anneeCondam);
            this.csbl.saveOne(condamnation);
            journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une condamnation :" + this.condamnation.getNumeroOrdre().concat(this.condamnation.getNumeroRp()));
            this.journalisation = new MethodeJournalisation();

            this.situation.setDatecreation(new Date());
            this.situation.setRowvers(new Date());
            this.situation.setTypesituation(typeSituation);
            this.situation.setPrison(prison);
            this.situation.setCondamnation(this.condamnation);
            this.ssbl.saveOne(situation);
            journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une situation :" + this.situation.getTypesituation());
            this.journalisation = new MethodeJournalisation();

            List<Statistique> statistiques = this.ssbl1.getBy("juridiction", "annee", this.juridiction, anneeCondam);
            if (statistiques.isEmpty()) {
                this.statistique.setJuridiction(this.juridiction);
                this.statistique.setAnnee(anneeCondam);
                this.statistique.setDatecreation(new Date());
                this.statistique.setRowvers(new Date());
                this.statistique.setNombreSaisiTotal(1L);
                this.ssbl1.saveOne(statistique);
                journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Enregistrement d'une statistique :" + this.statistique.getJuridiction().getLibellecourt() + " " + this.statistique.getAnnee().getCode());

            } else {
                Statistique stat = statistiques.get(0);
                stat.setRowvers(new Date());
                stat.setNombreSaisiTotal(stat.getNombreSaisiTotal()+1L);
                this.ssbl1.updateOne(stat);
                journalisation.saveLog4j(CondamnationBean.class.getName(), Level.INFO, "Mise à jour d'une statistqiue :" + stat.getJuridiction().getLibellecourt() + " " + stat.getAnnee().getCode());

            }

            this.journalisation = new MethodeJournalisation();
            this.condamnation = new Condamnation();
            this.juridiction = new Juridiction();
            this.personne = new Personne();
            this.situation = new Situation();
            this.infraction = new Infraction();
            this.prison = new Prison();
            this.peine = new Peine();
            this.statistique = new Statistique();

            //  SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
            // Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : "condamnation/saisie_condamnation.xhtml");
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            tx.commit();

        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
            try {
                tx.rollback();
            } catch (IllegalStateException ex) {
                Logger.getLogger(CondamnationBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(CondamnationBean.class.getName()).log(Level.FATAL, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(CondamnationBean.class.getName()).log(Level.FATAL, null, ex);
            }
        }

    }

    public void getObject(Long id) {
        this.juridiction = this.jsbl.find(id);
    }

    public Condamnation getCondamnation() {
        return condamnation;
    }

    public void setCondamnation(Condamnation condamnation) {
        this.condamnation = condamnation;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Infraction getInfraction() {
        return infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public Peine getPeine() {
        return peine;
    }

    public void setPeine(Peine peine) {
        this.peine = peine;
    }

    public List<Condamnation> getCondamnations() {
        this.condamnations = this.csbl.getAll();
        return condamnations;
    }

    public void setCondamnations(List<Condamnation> condamnations) {
        this.condamnations = condamnations;
    }

    public List<Condamnation> getCondamnationsFilter() {
        return condamnationsFilter;
    }

    public void setCondamnationsFilter(List<Condamnation> condamnationsFilter) {
        this.condamnationsFilter = condamnationsFilter;
    }

    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    public List<Juridiction> getJuridictions() {
        this.juridictions = this.jsbl.getAll();
        return juridictions;
    }

    public void setJuridictions(List<Juridiction> juridictions) {
        this.juridictions = juridictions;
    }

    public List<Situation> getSituations() {
        this.situations = this.ssbl.getAll();
        return situations;
    }

    public void setSituations(List<Situation> situations) {
        this.situations = situations;
    }

    public List<Infraction> getInfractions() {
        this.infractions = this.isbl.getAll();
        return infractions;
    }

    public void setInfractions(List<Infraction> infractions) {
        this.infractions = infractions;
    }

    public List<Prison> getPrisons() {
        this.prisons = this.psbl1.getAll();
        return prisons;
    }

    public void setPrisons(List<Prison> prisons) {
        this.prisons = prisons;
    }

    public List<Peine> getPeines() {
        this.peines = this.psbl2.getAll();
        return peines;
    }

    public void setPeines(List<Peine> peines) {
        this.peines = peines;
    }

    public String getTypeSituation() {
        return typeSituation;
    }

    public void setTypeSituation(String typeSituation) {
        this.typeSituation = typeSituation;
    }

    public Boolean getDet() {
        return det;
    }

    public void setDet(Boolean det) {
        this.det = det;
    }

    public Boolean getLib() {
        return lib;
    }

    public void setLib(Boolean lib) {
        this.lib = lib;
    }

    public Boolean getFuit() {
        return fuit;
    }

    public void setFuit(Boolean fuit) {
        this.fuit = fuit;
    }

    public MethodeJournalisation getJournalisation() {
        return journalisation;
    }

    public void setJournalisation(MethodeJournalisation journalisation) {
        this.journalisation = journalisation;
    }

    public Statistique getStatistique() {
        return statistique;
    }

    public void setStatistique(Statistique statistique) {
        this.statistique = statistique;
    }

    public CondamnationServiceBeanLocal getCsbl() {
        return csbl;
    }

    public void setCsbl(CondamnationServiceBeanLocal csbl) {
        this.csbl = csbl;
    }

    public PersonneServiceBeanLocal getPsbl() {
        return psbl;
    }

    public void setPsbl(PersonneServiceBeanLocal psbl) {
        this.psbl = psbl;
    }

    public JuridictionServiceBeanLocal getJsbl() {
        return jsbl;
    }

    public void setJsbl(JuridictionServiceBeanLocal jsbl) {
        this.jsbl = jsbl;
    }

    public SituationServiceBeanLocal getSsbl() {
        return ssbl;
    }

    public void setSsbl(SituationServiceBeanLocal ssbl) {
        this.ssbl = ssbl;
    }

    public InfractionServiceBeanLocal getIsbl() {
        return isbl;
    }

    public void setIsbl(InfractionServiceBeanLocal isbl) {
        this.isbl = isbl;
    }

    public PrisonServiceBeanLocal getPsbl1() {
        return psbl1;
    }

    public void setPsbl1(PrisonServiceBeanLocal psbl1) {
        this.psbl1 = psbl1;
    }

    public PeineServiceBeanLocal getPsbl2() {
        return psbl2;
    }

    public void setPsbl2(PeineServiceBeanLocal psbl2) {
        this.psbl2 = psbl2;
    }

    public List<Situation> getSituationsFileter() {
        return situationsFileter;
    }

    public void setSituationsFileter(List<Situation> situationsFileter) {
        this.situationsFileter = situationsFileter;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public List<Annee> getAnnees() {
        this.annees = this.asbl.getAll();
        return annees;
    }

    public void setAnnees(List<Annee> annees) {
        this.annees = annees;
    }

    public AnneServiceBeanLocal getAsbl() {
        return asbl;
    }

    public void setAsbl(AnneServiceBeanLocal asbl) {
        this.asbl = asbl;
    }

}
