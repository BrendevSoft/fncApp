/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Date;
import java.util.Objects;
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
@Table(name = "statistique")
@NamedQueries({
    @NamedQuery(name = "Statistique.findAll", query = "SELECT s FROM Statistique s")})
public class Statistique extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_saisi")
    private Integer nombreSaisi;

    @Column(name = "nombre_infraction")
    private Integer nombreInfraction;

    @Column(name = "nombre_saisi_total")
    private Long nombreSaisiTotal;

    @Column(name = "nombre_infraction_total")
    private Long nombreInfractionTotal;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @JoinColumn(name = "annee")
    @ManyToOne
    private Annee annee;

    @JoinColumn(name = "infraction")
    @ManyToOne
    private Infraction infraction;

    @JoinColumn(name = "juridiction")
    @ManyToOne
    private Juridiction juridiction;

    public Statistique() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getNombreSaisiTotal() {
        return nombreSaisiTotal;
    }

    public void setNombreSaisiTotal(Long nombreSaisiTotal) {
        this.nombreSaisiTotal = nombreSaisiTotal;
    }

    public Long getNombreInfractionTotal() {
        return nombreInfractionTotal;
    }

    public void setNombreInfractionTotal(Long nombreInfractionTotal) {
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

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Infraction getInfraction() {
        return infraction;
    }

    public void setInfraction(Infraction infraction) {
        this.infraction = infraction;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Statistique other = (Statistique) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Statistique{" + "id=" + id + ", nombreSaisi=" + nombreSaisi + ", nombreInfraction=" + nombreInfraction + ", nombreSaisiTotal=" + nombreSaisiTotal + ", nombreInfractionTotal=" + nombreInfractionTotal + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", annee=" + annee + ", infraction=" + infraction + ", juridiction=" + juridiction + '}';
    }

}
