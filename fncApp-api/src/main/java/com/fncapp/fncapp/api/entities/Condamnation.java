/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fncapp.fncapp.api.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Condamnation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
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
    @JoinColumn(name = "annee_code", referencedColumnName = "code")
    @ManyToOne
    private Annee anneeCode;
    @JoinColumn(name = "juridiction_code", referencedColumnName = "code")
    @ManyToOne(optional = false)
    private Juridiction juridictionCode;
    @JoinColumn(name = "peine_id", referencedColumnName = "id")
    @ManyToOne
    private Peine peineId;
    @JoinColumn(name = "personne_id", referencedColumnName = "id")
    @ManyToOne
    private Personne personneId;
    @OneToMany(mappedBy = "condamnationId")
    private Collection<Situation> situationCollection;

    public Condamnation() {
    }

    public Condamnation(String id) {
        this.id = id;
    }

    public Condamnation(String id, Date datejugement) {
        this.id = id;
        this.datejugement = datejugement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Annee getAnneeCode() {
        return anneeCode;
    }

    public void setAnneeCode(Annee anneeCode) {
        this.anneeCode = anneeCode;
    }

    public Juridiction getJuridictionCode() {
        return juridictionCode;
    }

    public void setJuridictionCode(Juridiction juridictionCode) {
        this.juridictionCode = juridictionCode;
    }

    public Peine getPeineId() {
        return peineId;
    }

    public void setPeineId(Peine peineId) {
        this.peineId = peineId;
    }

    public Personne getPersonneId() {
        return personneId;
    }

    public void setPersonneId(Personne personneId) {
        this.personneId = personneId;
    }

    public Collection<Situation> getSituationCollection() {
        return situationCollection;
    }

    public void setSituationCollection(Collection<Situation> situationCollection) {
        this.situationCollection = situationCollection;
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
        if (!(object instanceof Condamnation)) {
            return false;
        }
        Condamnation other = (Condamnation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fncapp.fncapp.api.entities.Condamnation[ id=" + id + " ]";
    }
    
}
