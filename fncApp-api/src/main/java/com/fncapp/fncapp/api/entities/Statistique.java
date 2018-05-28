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
@Table(name = "statistique")
@NamedQueries({
    @NamedQuery(name = "Statistique.findAll", query = "SELECT s FROM Statistique s")})
public class Statistique implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Column(name = "nombre_saisi")
    private Integer nombreSaisi;
    @Column(name = "nombre_infraction")
    private Integer nombreInfraction;
    @Column(name = "nombre_saisi_total")
    private Integer nombreSaisiTotal;
    @Column(name = "nombre_infraction_total")
    private Integer nombreInfractionTotal;
    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;
    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;
    @JoinColumn(name = "annee_code", referencedColumnName = "code")
    @ManyToOne
    private Annee anneeCode;
    @JoinColumn(name = "infraction_code", referencedColumnName = "code")
    @ManyToOne
    private Infraction infractionCode;
    @JoinColumn(name = "juridiction_code", referencedColumnName = "code")
    @ManyToOne
    private Juridiction juridictionCode;

    public Statistique() {
    }

    public Statistique(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getNombreSaisi() {
        return nombreSaisi;
    }

    public void setNombreSaisi(Integer nombreSaisi) {
        this.nombreSaisi = nombreSaisi;
    }

    public Integer getNombreInfraction() {
        return nombreInfraction;
    }

    public void setNombreInfraction(Integer nombreInfraction) {
        this.nombreInfraction = nombreInfraction;
    }

    public Integer getNombreSaisiTotal() {
        return nombreSaisiTotal;
    }

    public void setNombreSaisiTotal(Integer nombreSaisiTotal) {
        this.nombreSaisiTotal = nombreSaisiTotal;
    }

    public Integer getNombreInfractionTotal() {
        return nombreInfractionTotal;
    }

    public void setNombreInfractionTotal(Integer nombreInfractionTotal) {
        this.nombreInfractionTotal = nombreInfractionTotal;
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

    public Annee getAnneeCode() {
        return anneeCode;
    }

    public void setAnneeCode(Annee anneeCode) {
        this.anneeCode = anneeCode;
    }

    public Infraction getInfractionCode() {
        return infractionCode;
    }

    public void setInfractionCode(Infraction infractionCode) {
        this.infractionCode = infractionCode;
    }

    public Juridiction getJuridictionCode() {
        return juridictionCode;
    }

    public void setJuridictionCode(Juridiction juridictionCode) {
        this.juridictionCode = juridictionCode;
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
        if (!(object instanceof Statistique)) {
            return false;
        }
        Statistique other = (Statistique) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Statistique[ id=" + id + " ]";
    }
    
}
