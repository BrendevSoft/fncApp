/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
public class Situation extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @JoinColumn(name = "condamnation")
    @ManyToOne
    private Condamnation condamnation;

    @JoinColumn(name = "prison")
    @ManyToOne
    private Prison prison;

    public Situation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Condamnation getCondamnation() {
        return condamnation;
    }

    public void setCondamnation(Condamnation condamnation) {
        this.condamnation = condamnation;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Situation other = (Situation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Situation{" + "id=" + id + ", typesituation=" + typesituation + ", numMandatArret=" + numMandatArret + ", numMandatDepot=" + numMandatDepot + ", numDecisionLp=" + numDecisionLp + ", dateMandatArret=" + dateMandatArret + ", dateMandatDepot=" + dateMandatDepot + ", dateDecisionLp=" + dateDecisionLp + ", numEcrou=" + numEcrou + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", condamnation=" + condamnation + ", prison=" + prison + '}';
    }

}
