/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "situation")
@NamedQueries({
    @NamedQuery(name = "Situation.findAll", query = "SELECT s FROM Situation s")})
public class Situation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "typesituation")
    private String typesituation;
    @Column(name = "num_mandat_arret")
    private String numMandatArret;
    @Column(name = "num_mandat_depot")
    private String numMandatDepot;
    @Column(name = "num_decision_lp")
    private String numDecisionLp;
    @Column(name = "date_mandat_arret")
    @Temporal(TemporalType.DATE)
    private Date dateMandatArret;
    @Column(name = "date_mandat_depot")
    @Temporal(TemporalType.DATE)
    private Date dateMandatDepot;
    @Column(name = "date_decision_lp")
    @Temporal(TemporalType.DATE)
    private Date dateDecisionLp;
    @Column(name = "num_ecrou")
    private String numEcrou;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;
    @JoinColumn(name = "condamnation_id", referencedColumnName = "id")
    @ManyToOne
    private Condamnation condamnationId;
    @JoinColumn(name = "prison_id", referencedColumnName = "id")
    @ManyToOne
    private Prison prisonId;

    public Situation() {
    }

    public Situation(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypesituation() {
        return typesituation;
    }

    public void setTypesituation(String typesituation) {
        this.typesituation = typesituation;
    }

    public String getNumMandatArret() {
        return numMandatArret;
    }

    public void setNumMandatArret(String numMandatArret) {
        this.numMandatArret = numMandatArret;
    }

    public String getNumMandatDepot() {
        return numMandatDepot;
    }

    public void setNumMandatDepot(String numMandatDepot) {
        this.numMandatDepot = numMandatDepot;
    }

    public String getNumDecisionLp() {
        return numDecisionLp;
    }

    public void setNumDecisionLp(String numDecisionLp) {
        this.numDecisionLp = numDecisionLp;
    }

    public Date getDateMandatArret() {
        return dateMandatArret;
    }

    public void setDateMandatArret(Date dateMandatArret) {
        this.dateMandatArret = dateMandatArret;
    }

    public Date getDateMandatDepot() {
        return dateMandatDepot;
    }

    public void setDateMandatDepot(Date dateMandatDepot) {
        this.dateMandatDepot = dateMandatDepot;
    }

    public Date getDateDecisionLp() {
        return dateDecisionLp;
    }

    public void setDateDecisionLp(Date dateDecisionLp) {
        this.dateDecisionLp = dateDecisionLp;
    }

    public String getNumEcrou() {
        return numEcrou;
    }

    public void setNumEcrou(String numEcrou) {
        this.numEcrou = numEcrou;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public Date getRowvers() {
        return rowvers;
    }

    public void setRowvers(Date rowvers) {
        this.rowvers = rowvers;
    }

    public Condamnation getCondamnationId() {
        return condamnationId;
    }

    public void setCondamnationId(Condamnation condamnationId) {
        this.condamnationId = condamnationId;
    }

    public Prison getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Prison prisonId) {
        this.prisonId = prisonId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Situation)) {
            return false;
        }
        Situation other = (Situation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Situation[ id=" + id + " ]";
    }
    
}
