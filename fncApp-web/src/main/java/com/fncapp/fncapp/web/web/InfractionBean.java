/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.Constante;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.Infraction;
import com.fncapp.fncapp.api.service.InfractionServiceBeanLocal;
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

/**
 *
 * @author Brendev
 */
@Named(value = "infractionBean")
@ViewScoped
public class InfractionBean implements Serializable {

    private Infraction infraction;
    private List<Infraction> infractions;
    private List<Infraction> infractionsfilter;

    @EJB
    private InfractionServiceBeanLocal isbl;

    /**
     * Creates a new instance of InfractionBean
     */
    public InfractionBean() {
        this.infraction = new Infraction();
        this.infractions = new ArrayList<>();
    }

    public void cancel(ActionEvent actionEvent) {
        this.infraction = new Infraction();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.infraction.getId() == null) {
                this.infraction.setDatecreation(new Date());
                this.infraction.setRowvers(new Date());
                this.isbl.saveOne(infraction);
                context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            } else {
                this.infraction.setRowvers(new Date());
                this.isbl.updateOne(infraction);
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.infraction = new Infraction();

        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
        }

    }

    public void getObject(Long id) {
        this.infraction = this.isbl.find(id);
    }

    public Infraction getInfraction() {
        return infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    public List<Infraction> getInfractions() {
        this.infractions = this.isbl.getAll();
        return infractions;
    }

    public void setInfractions(List<Infraction> infractions) {
        this.infractions = infractions;
    }

    public List<Infraction> getInfractionsfilter() {
        return infractionsfilter;
    }

    public void setInfractionsfilter(List<Infraction> infractionsfilter) {
        this.infractionsfilter = infractionsfilter;
    }

    public InfractionServiceBeanLocal getIsbl() {
        return isbl;
    }

    public void setIsbl(InfractionServiceBeanLocal isbl) {
        this.isbl = isbl;
    }

}
