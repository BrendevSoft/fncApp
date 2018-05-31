/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.web.web;

import com.fncapp.fncapp.api.api.utils.Constante;
import com.fncapp.fncapp.api.entities.Groupe;
import com.fncapp.fncapp.api.entities.Juridiction;
import com.fncapp.fncapp.api.service.JuridictionServiceBeanLocal;
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
@Named(value = "juridictionBean")
@ViewScoped
public class JuridictionBean implements Serializable{

    private Juridiction juridiction;
    private List<Juridiction> juridictions;
    private List<Juridiction> juridictionsFilter;

    @EJB
    private JuridictionServiceBeanLocal jsbl;

    /**
     * Creates a new instance of JuridictionBean
     */
    public JuridictionBean() {
        this.juridiction = new Juridiction();
        this.juridictions = new ArrayList<>();
    }

    public void cancel(ActionEvent actionEvent) {
        this.juridiction = new Juridiction();
    }

    public void save(ActionEvent actionEvent) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.juridiction.getId() == null) {
                this.juridiction.setDatecreation(new Date());
                this.juridiction.setRowvers(new Date());
                this.jsbl.saveOne(juridiction);
                context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_REUSSIT));
            } else {
                this.juridiction.setRowvers(new Date());
                this.jsbl.updateOne(juridiction);
                context.addMessage(null, new FacesMessage(Constante.MODIFICATION_REUSSIT));
            }
            this.juridiction = new Juridiction();

        } catch (Exception e) {
            e.getMessage();
            context.addMessage(null, new FacesMessage(Constante.ENREGISTREMENT_ECHOUE));
        }

    }

    public void getObject(Long id) {
        this.juridiction = this.jsbl.find(id);
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public List<Juridiction> getJuridictions() {
        this.juridictions = this.jsbl.getAll();
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

}
