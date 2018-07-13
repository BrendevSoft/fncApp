/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Brendev
 */
@Entity
@Table(name = "peine")
@NamedQueries({
    @NamedQuery(name = "Peine.findAll", query = "SELECT p FROM Peine p")})
public class Peine extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "libelle")
    private String libelle;

    @Column(name = "amande")
    private String amande;

    @Column(name = "peine_is")
    private String peineIs;

    @Column(name = "sursis")
    private String sursis;

    @Column(name = "is_sejour")
    private String is_sejour;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @OneToMany(mappedBy = "peine")
    private Collection<Condamnation> condamnationCollection;

    public Peine() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAmande() {
        return amande;
    }

    public void setAmande(String amande) {
        this.amande = amande;
    }

    public String getPeineIs() {
        return peineIs;
    }

    public void setPeineIs(String peineIs) {
        this.peineIs = peineIs;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public String getSursis() {
        return sursis;
    }

    public void setSursis(String sursis) {
        this.sursis = sursis;
    }

    public String getIs_sejour() {
        return is_sejour;
    }

    public void setIs_sejour(String is_sejour) {
        this.is_sejour = is_sejour;
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

    public Collection<Condamnation> getCondamnationCollection() {
        return condamnationCollection;
    }

    public void setCondamnationCollection(Collection<Condamnation> condamnationCollection) {
        this.condamnationCollection = condamnationCollection;
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
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final Peine other = (Peine) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Peine{" + "id=" + id + ", libelle=" + libelle + ", amande=" + amande + ", peineIs=" + peineIs + ", sursis=" + sursis + ", is_sejour=" + is_sejour + ", datecreation=" + datecreation + ", rowvers=" + rowvers + '}';
    }

}
