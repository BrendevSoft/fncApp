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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "condamnation")
@NamedQueries({
    @NamedQuery(name = "Condamnation.findAll", query = "SELECT c FROM Condamnation c")})
public class Condamnation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "datejugement")
    @Temporal(TemporalType.DATE)
    private Date datejugement;

    @Column(name = "datecreation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datecreation;

    @Column(name = "rowvers")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rowvers;

    @Column(name = "numero_rp")
    private String numeroRp;

    @Column(name = "numero_ordre")
    private String numeroOrdre;

    @JoinColumn(name = "annee")
    @ManyToOne
    private Annee annee;

    @JoinColumn(name = "juridiction")
    @ManyToOne(optional = false)
    private Juridiction juridiction;

    @JoinColumn(name = "peine")
    @ManyToOne
    private Peine peine;

    @JoinColumn(name = "personne")
    @ManyToOne
    private Personne personne;

    @OneToMany(mappedBy = "condamnation")
    private Collection<Situation> situationCollection;

    public Condamnation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDatejugement() {
        return datejugement;
    }

    public void setDatejugement(Date datejugement) {
        this.datejugement = datejugement;
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

    public String getNumeroRp() {
        return numeroRp;
    }

    public void setNumeroRp(String numeroRp) {
        this.numeroRp = numeroRp;
    }

    public String getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(String numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public Annee getAnnee() {
        return annee;
    }

    public void setAnnee(Annee annee) {
        this.annee = annee;
    }

    public Juridiction getJuridiction() {
        return juridiction;
    }

    public void setJuridiction(Juridiction juridiction) {
        this.juridiction = juridiction;
    }

    public Peine getPeine() {
        return peine;
    }

    public void setPeine(Peine peine) {
        this.peine = peine;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Collection<Situation> getSituationCollection() {
        return situationCollection;
    }

    public void setSituationCollection(Collection<Situation> situationCollection) {
        this.situationCollection = situationCollection;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + Objects.hashCode(this.id);
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
        final Condamnation other = (Condamnation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condamnation{" + "id=" + id + ", datejugement=" + datejugement + ", datecreation=" + datecreation + ", rowvers=" + rowvers + ", numeroRp=" + numeroRp + ", numeroOrdre=" + numeroOrdre + ", annee=" + annee + ", juridiction=" + juridiction + ", peine=" + peine + ", personne=" + personne + '}';
    }

}
