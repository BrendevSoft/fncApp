/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.Constante;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.Prison;
import com.fncapp.fncapp.api.service.PrisonServiceBeanLocal;
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
@Named(value = "prisonBean")
@ViewScoped
public class PrisonBean implements Serializable{

    private Prison prison;
    private List<Prison> prisons;
    private List<Prison> prisonsFilter;

    @EJB
    private PrisonServiceBeanLocal psbl;

    /**
     * Creates a new instance of PrisonBean
     */
    public PrisonBean() {
        this.prison = new Prison();
        this.prisons = new ArrayList<>();
    }

    public void cancel(ActionEvent actionEvent) {
        this.prison = new Prison();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.prison.getId() == null) {
                this.prison.setDatecreation(new Date());
                this.prison.setRowvers(new Date());
                this.psbl.saveOne(prison);
                context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            } else {
                this.prison.setRowvers(new Date());
                this.psbl.updateOne(prison);
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.prison = new Prison();

        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
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
